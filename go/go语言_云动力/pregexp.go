package grepexp

func Match(s string, t []byte) (bool, error) {
	r := []byte(s)
	if r[0] == '^' {
		return matchhere(r[1:], t), nil
	}
	for i := 0; i < len(t); i++ {
		if matchhere(r, t[i:]) {
			return true, nil
		}
	}
	return false, nil
}
func matchhere(r, t []byte) bool {
	switch {
	case len(r) == 0:
		return true
	case len(r) > 1 && r[1] == '*':
		return matchstar(r[0], r[2:], t)
	case r[0] == '$' && len(r) == 1:
		return len(t) == 0
	case len(t) > 0 && (r[0] == '.' || r[0] == t[0]):
		return matchhere(r[1:], t[1:])
	}
	return false
}
func matchstar(c byte, r, t []byte) bool {
	for i := 0; i < len(t); i++ {
		if matchhere(r, t[i:]) {
			return true
		}
		if t[i] != c && c != '.' {
			break
		}
	}
	return false
}
