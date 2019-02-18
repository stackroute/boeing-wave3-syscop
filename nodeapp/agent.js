const request = require('request')
const { Tags, FORMAT_HTTP_HEADERS } = require('opentracing')
const tracer = require('./my-tracer') // jaeger etc.

// Request options
const uri = 'http://localhost:8082'
const method = 'GET'
const headers = {}

// Start a span
const span = tracer.startSpan('http_request')
span.setTag(Tags.HTTP_URL, uri)
span.setTag(Tags.HTTP_METHOD, method)

// Send span context via request headers (parent id etc.)
tracer.inject(span, FORMAT_HTTP_HEADERS, headers)

request({ uri, method, headers }, (err, res) => {
  // Error handling
  if (err) {
    span.setTag(Tags.ERROR, true)
    span.setTag(Tags.HTTP_STATUS_CODE, err.statusCode)
    span.log({
      event: 'error',
      message: err.message,
      err
    })
    span.finish()
    return
  }

  // Finish span
  span.setTag(Tags.HTTP_STATUS_CODE, res.statusCode)
  span.finish()
})
