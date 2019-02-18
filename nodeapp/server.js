'use strict';

const express = require('express');
const bodyParser = require('body-parser');
const Prometheus = require('./app/metrics/muzix.metrics.js');



// create express app
const app = express();

/**
 * This creates the module that we created in the step before.
 * In my case it is stored in the util folder.
 */
  
/**
 * The below arguments start the counter functions
 */
app.use(Prometheus.requestCounters);  
app.use(Prometheus.responseCounters);

/**
 * Enable metrics endpoint
 */
Prometheus.injectMetricsRoute(app);

/**
 * Enable collection of default metrics
 */
Prometheus.startCollection();  



// parse requests of content-type - application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: true }))

// parse requests of content-type - application/json
app.use(bodyParser.json())

// define a simple route
app.get('/', (req, res) => {
    res.json({"message": "Welcome to EasyNotes application. Take notes quickly. Organize and keep track of all your notes."});
});


require('./app/routes/muzix.routes.js')(app);
// listen for requests
app.listen(8082, () => {
    console.log("Server is listening on port 8082");
});

// Configuring the database
const dbConfig = require('./config/database.config.js');
const mongoose = require('mongoose');

mongoose.Promise = global.Promise;

// Connecting to the database
mongoose.connect(dbConfig.url, {
    useNewUrlParser: true
}).then(() => {
    console.log("Successfully connected to the database");    
}).catch(err => {
    console.log('Could not connect to the database. Exiting now...', err);
    process.exit();
});
