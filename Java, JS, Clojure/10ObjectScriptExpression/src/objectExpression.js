var VARS = {"x": 0, "y": 1, "z": 2};

function Variable(s) {
    var ind = VARS[s];
    this.getName = function () {
        return s
    };
    this.getInd = function () {
        return ind
    }
}
Variable.prototype.toString = function () {
    return this.getName()
};
Variable.prototype.evaluate = function () {
    return arguments[this.getInd()]
};

function Const(x) {
    this.getValue = function () {
        return x
    }
}
Const.prototype.toString = function () {
    return this.getValue().toString()
};
Const.prototype.evaluate = function () {
    return this.getValue()
};

function Operation() {
    var operands = [].slice.apply(arguments);
    this.getOperands = function () {
        return operands
    }
}
Operation.prototype.toString = function () {
    return this.getOperands().join(" ") + " " + this.getSymbol()
};
Operation.prototype.evaluate = function() {
    var args = arguments;
    var res = this.getOperands().map(function (value) {
        return value.evaluate.apply(value, args)
    });
    return this.action.apply(null, res)
};

function MakeOperation(func, symbol) {
    this.action = func;
    this.getSymbol = function () {
        return symbol
    }
}

MakeOperation.prototype = Operation.prototype;

function operation(func, symbol) {
    var result = function () {
        Operation.apply(this, arguments)
    };
    result.prototype = new MakeOperation(func, symbol);
    return result;
}

var Negate = operation(function (a) {return -a}, "negate");

var Add = operation(function (a, b) {return a + b}, "+");

var Multiply = operation(function (a, b) {return a * b}, "*");

var Subtract = operation(function (a, b) {return a - b}, "-");

var Divide = operation(function (a, b) {return a / b}, "/");

var Sqrt = operation(function (a) {return Math.pow(Math.abs(a), 1 / 2)}, "sqrt");

var Square = operation(function (a) {return Math.pow(a, 2)}, "square");