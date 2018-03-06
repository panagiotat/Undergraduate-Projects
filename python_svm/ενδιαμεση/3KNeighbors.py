

from keras.datasets import mnist
from sklearn import preprocessing
from sklearn.neighbors import KNeighborsClassifier



(X_train, y_train), (X_test, y_test) = mnist.load_data()

x_train=X_train.reshape(60000,28*28)
x_test=X_test.reshape(10000,28*28)

scaler= preprocessing.MinMaxScaler()
scaler.fit(x_train)
x_train = scaler.transform(x_train)
x_test = scaler.transform(x_test )


model= KNeighborsClassifier(n_neighbors=3)
model.fit(x_train,y_train)


print ("Accuracy for the test set: ",  model.score(x_test,y_test) )






