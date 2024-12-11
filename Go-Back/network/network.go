package network

import (
	"log"

	"github.com/gin-gonic/gin"
)

type Network struct {
	engin *gin.Engine
}

// Network가 사용할 수 있는 메소드다
func NewServer() *Network {
	n := Network{engin: gin.New()}
	return &n
}

// Nework 가 사용할 수 있는 메소드다
func (n *Network) StartServer() error {
	log.Println("Starting Server")
	return n.engin.Run(":8080")
}
