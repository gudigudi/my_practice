<?php
function flush_gzip() {
    $contents = ob_get_contents();
    ob_end_clean();
    header('Content-Type: text/html');
    header('Content-Encoding: gzip');
    header('Cache-Control: no-cache');
    header('Expires: -1');
    print("\x1f\x8b\x08\x00\x00\x00\x00");
    $size = strlen($contents);
    $contents = gzcompress($contents, 9);
    $contents = substr($contents, 0, $size);
    print($contents);
}

ob_start();
ob_implicit_flush(0);
?>

<html>
<body>
<script>
    document.cookie="supports_gzip=1";
</script>
</body>
</html>

<?php
flush_gzip();
?>