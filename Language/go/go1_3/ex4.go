package main

import (
	"log"
	"os"
)

type Job struct {
	Command string
	*log.Logger
}

func NewJob(Command string) *Job {
	return &Job{command, log.New(os.Stderr, "Job: ", log.Ldate)}
}

func main() {
	NewJob("demo").Print("starting now...")
}
