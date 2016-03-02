## How to refer to the correct this
Don't use this
You actually don't want to access this in particular, but the object it refers to. That's why an easy solution is to simply create a new variable that also refers to that object. The variable can have any name, but common ones are self and that.

function MyConstructor(data, transport) {
    this.data = data;
    var self = this;
    transport.on('data', function() {
        alert(self.data);
    });
}

var self = this;
document.body.onclick = function() {
    self.method();
};


or use an arrow function:

document.body.onclick = () => this.method();
