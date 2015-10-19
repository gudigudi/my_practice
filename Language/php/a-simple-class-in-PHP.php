<?php
class Account {
    protected $balance;
    protected $minimum;

    public function __construct($amount, $lowest)
    {
        // Initialize the data members when a new object in instantiated.
        $this->balance = $amount;
        $this->minimum = $minimum;
    }

    public function deposit($amount)
    {
        $this->balance += $amount;
    }

    public function withdraw($amount)
    {
        $this->balance -= $amount;
    }
}
?>
