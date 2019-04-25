"use strict"




var operation = function(action) {
    return function() {
        var operands = arguments;
        return function() {
            var res = [];
            for (var i = 0; i < operands.length; i++) {
                res.push(operands[i].apply(null, arguments));
            }
            return action.apply(null, res);
        }
    }
}

var cnst = function(value) {
    return function() {
        return value;
    }
}

var vars = {
    "x": 0,
    "y": 1,
    "z": 2
};

var variable = function(name) {
    return function() {
        return arguments[vars[name]];
    }
}

var add = operation(function(a, b) { return a + b; })

var subtract = operation(function(a, b) { return a - b; })

var multiply = operation(function(a, b) { return a * b; })

var divide = operation(function(a, b) { return a / b; })

var negate = operation(function(a) { return -a; })

var cube = operation(function(a) { return Math.pow(a, 3); })

var cuberoot = operation(function(a) { return Math.pow(a, 1 / 3); })

