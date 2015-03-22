// Package bitcoin provides ...
package bitcoin

import (
	"crypto/sha256"
	"hash"
)

var sha, sha2 hash.Hash

func init() {
	sha = sha256.New()
	sha2 = sha256.New() // has twice
}

func Has(ba []byte) []byte {
	sha.Reset()
	sha2.Reset()
	ba = sha.Sum(ba)
	return sha2.Sum(ba)
}
