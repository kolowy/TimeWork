// routes/auth.routes.js

const express = require("express");
const jwt = require("jsonwebtoken");
const bcrypt = require("bcrypt");
const router = express.Router();
const userSchema = require("../models/User");
const calendarSchema = require("../models/Calendar");
const authorize = require("../middlewares/auth");
const { check, validationResult } = require('express-validator');


//NOTE Get Single User
router.route('/user-calendar/').get(authorize, (req, res) => {
    console.log(req.user.userId)

    calendarSchema.findOne({
        userID: req.user.userId
    }).then(response => {
        if (!response) {
            return res.status(401).json({
                message: "Calendar failed"
            });
        }
        res.status(200).json({
            response
        });
    })
})



module.exports = router;