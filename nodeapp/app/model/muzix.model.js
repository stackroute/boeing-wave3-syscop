const mongoose = require('mongoose');

const MuzixSchema = mongoose.Schema({
    trackId: String,
    trackName: String,
    trackComments: String
}, {
    timestamps: true
});

module.exports = mongoose.model('Muzix', MuzixSchema, 'mymuzix');

