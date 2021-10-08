// models/Calendar.js

const mongoose = require('mongoose');
const Schema = mongoose.Schema;
const uniqueValidator = require('mongoose-unique-validator');


const classShema = new Schema({ 
    subject: 'string', 
    startTime:'string', 
    endTime:'string' 
});


let CalendarSchema = new Schema({
    id: {
        type: String,
        unique: true
    },
    classroom: {
        monday: [classShema],
        tuesday: [classShema],
        wednesday: [classShema],
        thurday: [classShema],
        friday: [classShema],
        saterday: [classShema],
        sunday: [classShema]
    }
}, {
    collection: 'calendars'
})

CalendarSchema.plugin(uniqueValidator, { message: 'You already have an calendar' });
module.exports = mongoose.model('Calendar', CalendarSchema)