<?php
class person {
    private $firstname;
    private $lastname;
    private $gender;

    public function getFirstname() { return $this->firstname; }
    public function getLastname()  { return $this->lastname;  }
    public function getGender()    { return $this->gender;    }

    public function setFirstname($firstname) { $this->firstname=$firstname; }
    public function setLastname($lastname)   { $this->lastname=$lastname;   }
    public function setGender($gender)       { $this->gender=$gender;       }

} // person类结束

$newPerson = new person();
$newPerson->setFirstname("Peter");
$newPerson->setLastname("MacIntyre");
$newPerson->setGender("male");

echo "the Person class currently has these values: ";
echo "<br /> First Name:" . $newPerson->getFirstname();
echo "<br /> Last Name:" . $newPerson->getLastname();
echo "<br /> Gender:" . $newPerson->getGender();
?>
