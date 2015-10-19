var fs = require('fs');
fs.readFile('sample01.txt', 'utf-8', function (err, data) {
	fs.readFile('sample02.txt', 'utf-8' function (err, data) {
        fs.readFile('sample03.txt', 'utf-8' function (err, data) {
            fs.readFile('sample04.txt', 'utf-8' function (err, data) {

            });
        });
    });
});
