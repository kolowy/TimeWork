// middlewares/auth.js

const jwt = require("jsonwebtoken");

module.exports = (req, res, next) => {
    try {
        const token = req.headers.authorization.split(" ")[1];
        jwt.verify(token, "longer-secret-is-better", (err, value) => {
            if (!value) {
                res.status(500).json({ error: 'token undefined' })
            } else {
                if (err) {
                    res.status(500).json({ error: 'failed to authenticate token' })
                } else {
                    req.user = value
                }
            }
            next()
        });
    } catch (error) {
        console.log(error)
        res.status(401).json({ message: "No token provided" });
    }
};