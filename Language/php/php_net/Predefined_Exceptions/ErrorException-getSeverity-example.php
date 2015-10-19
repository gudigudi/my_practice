<?php
try {
    throw new ErrorException("Exception message", 0, 75);
} catch (Exception $e) {
    echo "This exception severity is: "  .$e->getSeverity();
}

?>
