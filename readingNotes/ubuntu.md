### 隐私 ###
sudo: run command as root 
passed: change your password

### network ###
ifconfig: show network information  
iwconfig: show wireless information  
sudo iwlist scan: scan for wireless networks  
sudo /etc/init.d/networking restart: reset network for manual configurations  
/etc/network/interfaces: manual configuration  
ifup interface: bring interface online  
ifdown interface: disable interface 

### system services ###
sudo service nginx start/stop: start/stop service nginx  
sudo /etc/init.d/nginx restart: restart inited service nginx  

### package management ###
sudo apt-get update: refresh avaailable updates  
sudo apt-get upgrade: upgrade all packages  
sudo apt-get install pkg  
sudo apt-get purge pkg: remove pkg and its configurations 
dpkg -i pkg.deb: isntall file pkg.deb

