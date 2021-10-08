// routes/auth.routes.js

const express = require("express");
const jwt = require("jsonwebtoken");
const bcrypt = require("bcrypt");
const router = express.Router();
const userSchema = require("../models/User");
const authorize = require("../middlewares/auth");
const { check, validationResult } = require('express-validator');
const calendar = require('../data/calendar.json')


// Get Single User
router.route('/user-calendar/').get(authorize, (req, res, next) => {
    res.status(200).json(calendar)
})



module.exports = router;