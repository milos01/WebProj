var express = require('express');
var app = express();
var server = require('http').createServer(app);
var io = require('socket.io').listen(server);
connections = [];


app.set('port', process.env.PORT || 8080)
server.listen(3000);
console.log("Server running...");

io.sockets.on('connection', function(socket){
	connections.push(socket);
	console.log("Connected: "+ socket.id);
	
	socket.on('disconnect', function(){
		connections.splice(connections.indexOf(socket),1);
		console.log("Disconnected: "+ socket.id);
	});
	
	socket.on('reserve', function(data){
		console.log(data);
		io.sockets.emit('reserveTable',{id:data});
	});
	
	socket.on('closeReserve', function(data){
		io.sockets.emit('closeReserveTable',{id:data});
	})
});