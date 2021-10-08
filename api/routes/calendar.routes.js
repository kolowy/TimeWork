// routes/auth.routes.js

const express = require("express");
const jwt = require("jsonwebtoken");
const bcrypt = require("bcrypt");
const router = express.Router();
const userSchema = require("../models/User");
const calendarSchema = require("../models/Calendar");
const authorize = require("../middlewares/auth");
const { check, validationResult } = require('express-validator');


// Get Single User
router.route('/user-calendar/').get(authorize, (req, res, next) => {
    userSchema.find((error, response) => {
        if (error) {
            return next(error)
        } else {
            calendarSchema.findOne({
                id: response._id
            }).then(response => {        
                if (error) {
                    return next(error)
                } else {
                    res.status(200).json(response)
                }
            })        
        }
    })
})



module.exports = router;