import numpy as np
import tensorflow as tf

sess = tf.Session()

identify_matrix = tf.diag([1.0, 1.0, 1.0])
A = tf.truncated_normal([2, 3])
B = tf.fill([2, 3], 5.0)
C = tf.random_uniform([3, 2])
D = tf.convert_to_tensor(np.array([
    [1., 2., 3.],
    [-3., -7., -1.],
    [0., 5., -2.]
]))

print(sess.run(identify_matrix))
print(sess.run(A))

sess.close()
