const express = require('express')
const app = express()
const http = require('http')
const {Server} = require('socket.io')
const cors = require('cors')

app.use(cors())
const server = http.createServer(app)

const io = new Server(server, {

    cors :{
        origin: "http://localhost:8082"
    }
})

io.on("connection", (socket) =>{

    console.log('Connected')
    socket.on("send_message", (data) =>{
        console.log(data)
        socket.broadcast.emit("receive_message",data)
    })
})

server.listen(8083, () =>{
    console.log("Server is running")
})