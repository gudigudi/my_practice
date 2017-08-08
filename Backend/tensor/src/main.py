import numpy as np
import matplotlib.pyplot as plt
# import seaborn as sns; sns.set()
from sklearn.datasets import make_blobs

x,y =make_blobs(100,2, centers=2,random_state=2,cluster_std=1.5)
plt.scatter(x[:,0],x[:,1],c=y,s=50,cmap='RdBu')