package main

import (
	"syscall"
)

const (
	LINUX_REBOOT_MAGIC1 uintptr = 0xfee1dead
	LINUX_REBOOT_MAGIC2 uintptr = 672274793
	LINUX_REBOOT_MAGIC3 uintptr = 0x1234567
)

func main() {
	syscall.Syscall(syscall.SYS_REBOOT, LINUX_REBOOT_MAGIC1, LINUX_REBOOT_MAGIC2, LINUX_REBOOT_MAGIC3)
}
