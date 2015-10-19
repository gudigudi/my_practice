#include <linux/init.h>
#include <linux/module.h>
#include <linux/kernel.h>

// Module License
MODULE LICENSE("GPL")

// module init function
static int hello_init(void)
{
    printk(KERN_ALERT "hello, I am edsionte\n");
    return 0;
}

// module exit function
static void hello_exit(void)
{
    printk(KERN_ALERT "goodbye, kernel\n");
}

module_init(hello_init);
module_exit(hello_exit);

// description about module
MODULE_AUTHOR("edsionte Wu");
MODULE_DESCRIPTION("This is a simple example!\n");
MODULE_ALIAS("A simplest example");
