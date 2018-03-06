
from keras.models import Sequential
from keras.datasets import mnist
from sklearn import preprocessing
from keras.layers import Dense
from keras.utils import np_utils
from sklearn.decomposition import PCA
import numpy




(X_train, y_train), (X_test, y_test) = mnist.load_data()

x_train=X_train.reshape(60000,28*28)
x_test=X_test.reshape(10000,28*28)

scaler= preprocessing.MinMaxScaler()
scaler.fit(x_train)
x_train = scaler.transform(x_train)
x_test = scaler.transform(x_test )


y_train = np_utils.to_categorical(y_train,10)
y_test = np_utils.to_categorical(y_test,10)

#1) first try with 10 epochs 

model = Sequential()
model.add(Dense(20, input_dim=784, activation='relu'))
model.add(Dense(20,  activation='relu'))
model.add(Dense(20,  activation='relu'))
model.add(Dense(10, activation='sigmoid'))

model.compile( loss=  'mean_squared_error', optimizer='adam', metrics=['accuracy'])

model.fit(x_train, y_train, epochs=10, batch_size=32,  verbose=1,validation_data=(x_test, y_test))



#2) second try with 500 epochs 

model = Sequential()
model.add(Dense(20, input_dim=784, activation='relu'))
model.add(Dense(20,  activation='relu'))
model.add(Dense(20,  activation='relu'))
model.add(Dense(10, activation='sigmoid'))

model.compile( loss=  'mean_squared_error', optimizer='adam', metrics=['accuracy'])

model.fit(x_train, y_train, epochs=500, batch_size=32,  verbose=1,validation_data=(x_test, y_test))

predictions = model.predict(x_train)

#3) Using pca with 100 dimensions 


(X_train, y_train), (X_test, y_test) = mnist.load_data()

x_train=X_train.reshape(60000,28*28)
x_test=X_test.reshape(10000,28*28)
scaler= preprocessing.MinMaxScaler()
scaler.fit(x_train)
x_train = scaler.transform(x_train)
x_test = scaler.transform(x_test )
pca = PCA(n_components=100)

x_train=pca.fit_transform(x_train)
x_test= pca.transform(x_test)

y_train = np_utils.to_categorical(y_train,10)
y_test = np_utils.to_categorical(y_test,10)



model = Sequential()
model.add(Dense(20, input_dim=100, activation='relu'))
model.add(Dense(20,  activation='relu'))
model.add(Dense(20,  activation='relu'))
model.add(Dense(10, activation='sigmoid'))

model.compile( loss=  'mean_squared_error', optimizer='adam', metrics=['accuracy'])

model.fit(x_train, y_train, epochs=10, batch_size=32,  verbose=1,validation_data=(x_test, y_test))

#4) Using pca with 50 dimensions

(X_train, y_train), (X_test, y_test) = mnist.load_data()

x_train=X_train.reshape(60000,28*28)
x_test=X_test.reshape(10000,28*28)
scaler= preprocessing.MinMaxScaler()
scaler.fit(x_train)
x_train = scaler.transform(x_train)
x_test = scaler.transform(x_test )
pca = PCA(n_components=50)

x_train=pca.fit_transform(x_train)
x_test= pca.transform(x_test)

y_train = np_utils.to_categorical(y_train,10)
y_test = np_utils.to_categorical(y_test,10)



model = Sequential()
model.add(Dense(20, input_dim=50, activation='relu'))
model.add(Dense(20,  activation='relu'))
model.add(Dense(20,  activation='relu'))
model.add(Dense(10, activation='sigmoid'))

model.compile( loss=  'mean_squared_error', optimizer='adam', metrics=['accuracy'])

model.fit(x_train, y_train, epochs=10, batch_size=32,  verbose=1,validation_data=(x_test, y_test))


#6) Using 10 hidden layers 


(X_train, y_train), (X_test, y_test) = mnist.load_data()

x_train=X_train.reshape(60000,28*28)
x_test=X_test.reshape(10000,28*28)
scaler= preprocessing.MinMaxScaler()
scaler.fit(x_train)
x_train = scaler.transform(x_train)
x_test = scaler.transform(x_test )




y_train = np_utils.to_categorical(y_train,10)
y_test = np_utils.to_categorical(y_test,10)



model = Sequential()
model.add(Dense(20, input_dim=784, activation='relu'))
model.add(Dense(20,  activation='relu'))
model.add(Dense(20,  activation='relu'))
model.add(Dense(20,  activation='relu'))
model.add(Dense(20,  activation='relu'))
model.add(Dense(20,  activation='relu'))
model.add(Dense(20,  activation='relu'))
model.add(Dense(20,  activation='relu'))
model.add(Dense(20,  activation='relu'))
model.add(Dense(20,  activation='relu'))
model.add(Dense(10, activation='sigmoid'))

model.compile( loss=  'mean_squared_error', optimizer='adam', metrics=['accuracy'])

model.fit(x_train, y_train, epochs=10, batch_size=32,  verbose=1,validation_data=(x_test, y_test))

#7) Using 1 hidden layer


(X_train, y_train), (X_test, y_test) = mnist.load_data()

x_train=X_train.reshape(60000,28*28)
x_test=X_test.reshape(10000,28*28)
scaler= preprocessing.MinMaxScaler()
scaler.fit(x_train)
x_train = scaler.transform(x_train)
x_test = scaler.transform(x_test )




y_train = np_utils.to_categorical(y_train,10)
y_test = np_utils.to_categorical(y_test,10)



model = Sequential()
model.add(Dense(10, input_dim=784, activation='relu'))
model.add(Dense(10, activation='sigmoid'))

model.compile( loss=  'mean_squared_error', optimizer='adam', metrics=['accuracy'])

model.fit(x_train, y_train, epochs=10, batch_size=32,  verbose=1,validation_data=(x_test, y_test))

#8) Using mean_squared_logarithmic_error


(X_train, y_train), (X_test, y_test) = mnist.load_data()

x_train=X_train.reshape(60000,28*28)
x_test=X_test.reshape(10000,28*28)
scaler= preprocessing.MinMaxScaler()
scaler.fit(x_train)
x_train = scaler.transform(x_train)
x_test = scaler.transform(x_test )




y_train = np_utils.to_categorical(y_train,10)
y_test = np_utils.to_categorical(y_test,10)



model = Sequential()
model.add(Dense(20, input_dim=784, activation='relu'))
model.add(Dense(20,  activation='relu'))
model.add(Dense(20,  activation='relu'))
model.add(Dense(10, activation='sigmoid'))

model.compile( loss=  'mean_squared_logarithmic_error', optimizer='adam', metrics=['accuracy'])

model.fit(x_train, y_train, epochs=10, batch_size=10,  verbose=1,validation_data=(x_test, y_test))

#9) Using different activation formats

(X_train, y_train), (X_test, y_test) = mnist.load_data()

x_train=X_train.reshape(60000,28*28)
x_test=X_test.reshape(10000,28*28)
scaler= preprocessing.MinMaxScaler()
scaler.fit(x_train)
x_train = scaler.transform(x_train)
x_test = scaler.transform(x_test )




y_train = np_utils.to_categorical(y_train,10)
y_test = np_utils.to_categorical(y_test,10)



model = Sequential()
model.add(Dense(20, input_dim=784, activation='tanh'))
model.add(Dense(20,  activation='softmax'))
model.add(Dense(20,  activation='selu'))
model.add(Dense(10, activation='linear'))

model.compile( loss=  'mean_squared_error', optimizer='adam', metrics=['accuracy'])

model.fit(x_train, y_train, epochs=10, batch_size=10,  verbose=1,validation_data=(x_test, y_test))

#10) After many tries i manage to reach 99% at the train set and more than 97% at the test set with this format

(X_train, y_train), (X_test, y_test) = mnist.load_data()

x_train=X_train.reshape(60000,28*28)
x_test=X_test.reshape(10000,28*28)
scaler= preprocessing.MinMaxScaler()
scaler.fit(x_train)
x_train = scaler.transform(x_train)
x_test = scaler.transform(x_test )


pca = PCA(n_components=100)

x_train=pca.fit_transform(x_train)
x_test= pca.transform(x_test)

y_train = np_utils.to_categorical(y_train,10)
y_test = np_utils.to_categorical(y_test,10)



model = Sequential()
model.add(Dense(200, input_dim=100, activation='relu'))
model.add(Dense(200,  activation='relu'))
model.add(Dense(200,  activation='relu'))
model.add(Dense(10, activation='sigmoid'))

model.compile( loss=  'mean_squared_error', optimizer='adam', metrics=['accuracy'])

model.fit(x_train, y_train, epochs=10, batch_size=32,  verbose=1,validation_data=(x_test, y_test))



x_test.shape

y_test.shape


