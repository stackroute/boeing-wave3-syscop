/*
	metric
*/
/*
const client = require('prom-client');

const collectDefaultMetrics = client.collectDefaultMetrics;


var dash = require('appmetrics-dash').attach()	

const Prometheus = require('prom-client')
const httpRequestDurationMicroseconds = new Prometheus.Histogram({
  name: 'http_request_duration_ms',
  help: 'Duration of HTTP requests in ms',
  labelNames: ['route'],
  // buckets for response time from 0.1ms to 500ms
  buckets: [0.10, 5, 15, 50, 100, 200, 300, 400, 500]
})

*/




const Muzix  = require('../model/muzix.model.js');

// Create and Save a new Track
exports.create = (req, res) => {
    // Validate request
    if(!req.body.trackId) {
        return res.status(400).send({
            message: "Track content can not be empty"
        });
    }

    // Create a Track
    const track = new Muzix({
        trackId: req.body.trackId, 
        trackName: req.body.trackName,
	trackComments: req.body.trackComments
    });

    // Save Track in the database
    track.save()
    .then(data => {
        res.send(data);
    }).catch(err => {
        res.status(500).send({
            message: err.message || "Some error occurred while creating the Note."
        });
    });
};

// Retrieve and return all tracks from the database.

// const end1 = httpRequestDurationMicroseconds.startTimer();
exports.findAll = (req, res) => {
    Muzix.find()
    .then( muzix => {
        res.send(muzix);
    }).catch(err => {
        res.status(500).send({
            message: err.message || "Some error occurred while retrieving notes."
        });
    });
	// end1();
};

//checking for connection
// const end = httpRequestDurationMicroseconds.startTimer();
exports.enter = (req, res) => {
	res.send("Hello");
	// end();
};
// Update a note identified by the noteId in the request
exports.update = (req, res) => {
    // Validate Request
    if(!req.body.content) {
        return res.status(400).send({
            message: "Note content can not be empty"
        });
    }

    // Find note and update it with the request body
    Note.findByIdAndUpdate(req.params.id, {
        trackComments: req.body.comments
    }, {new: true})
    .then(track => {
        if(!track) {
            return res.status(404).send({
                message: "Note not found with id " + req.params.id
            });
        }
        res.send(track);
    }).catch(err => {
        if(err.kind === 'ObjectId') {
            return res.status(404).send({
                message: "Note not found with id " + req.params.id
            });
        }
        return res.status(500).send({
            message: "Error updating note with id " + req.params.id
        });
    });
};


// Delete a note with the specified noteId in the request
exports.delete = (req, res) => {
    Note.findByIdAndRemove(req.params.id)
    .then(track => {
        if(!track) {
            return res.status(404).send({
                message: "Note not found with id " + req.params.id
            });
        }
        res.send({message: "Note deleted successfully!"});
    }).catch(err => {
        if(err.kind === 'ObjectId' || err.name === 'NotFound') {
            return res.status(404).send({
                message: "Note not found with id " + req.params.id
            });
        }
        return res.status(500).send({
            message: "Could not delete note with id " + req.params.id
        });
    });
};

/*
//collecting metrics
exports.getmetrics = (req, res) => {
  collectDefaultMetrics();
  res.set('Content-Type', Prometheus.register.contentType)
  res.end(Prometheus.register.metrics())
  	
  

}

*/
//exports.getsystemmetrics = (req,res) => {

  //collectDefaultMetrics();	
//}
