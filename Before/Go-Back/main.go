package main

import (
	"go-chat/network"
	"log"
)

func init() {
	// main() 실행 전 수행

	log.Println("선수행")
}

func main() {
	n := network.NewServer()
	n.StartServer()
}
