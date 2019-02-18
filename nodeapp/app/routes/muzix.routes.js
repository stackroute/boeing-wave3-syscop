module.exports = (app) => {
    const muzix = require('../controllers/muzix.controller.js');
	
    //checking for connection
    app.get('/hi', muzix.enter);
    // Create a new Note
    app.post('/track', muzix.create);

    // Retrieve all Notes
    app.get('/tracks', muzix.findAll);

    // Retrieve a single Note with noteId
   // app.get('/track/:id', notes.findOne);

    // Update a Note with noteId
    app.put('/track/:comments/:id', muzix.update);

    // Delete a Note with noteId
    app.delete('/track/:id', muzix.delete);

    
    // app.get('/metrics', muzix.getmetrics);

    //app.get('/systemmetrics', muzix.getsystemmetrics);		
}
