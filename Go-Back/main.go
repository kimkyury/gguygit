package main

import "log"

func init() {
	// main() 실행 전 수행

	log.Println("선수행")
}

func main() {
	log.Println("후수행")
}
