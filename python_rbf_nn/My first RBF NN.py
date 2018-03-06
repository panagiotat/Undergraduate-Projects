from keras.datasets import mnist
from sklearn import preprocessing
from keras.utils import np_utils
from sklearn.metrics.pairwise import rbf_kernel
from keras.models import Sequential
from keras.layers import Dense
from sklearn.decomposition import PCA
from sklearn.gaussian_process.kernels import PairwiseKernel
from sklearn.gaussian_process import GaussianProcessRegressor
from sklearn.cluster import KMeans



(X_train, y_train), (X_test, y_test) = mnist.load_data()

x_train=X_train.reshape(60000,28*28)
x_test=X_test.reshape(10000,28*28)

scaler= preprocessing.MinMaxScaler()
scaler.fit(x_train)
x_train = scaler.transform(x_train)
x_test = scaler.transform(x_test )



#First try with standard model


pca = PCA(n_components=100)

x_train=pca.fit_transform(x_train)
x_test= pca.transform(x_test)


kmean= KMeans(n_clusters=10)
kmean.fit(x_train,y_train)


cent= kmean.predict(kmean.cluster_centers_)
cent = np_utils.to_categorical(cent,10)

gp_kernel = PairwiseKernel(gamma=1, metric= 'linear')
gp = GaussianProcessRegressor(kernel=gp_kernel )

gp.fit(kmean.cluster_centers_,cent)



t = gp.predict(x_train)
t2 = gp.predict(x_test)


model = Sequential()
model.add(Dense(20, input_dim=10, activation='relu'))
model.add(Dense(10,  activation='sigmoid'))
model.compile( loss=  'mean_squared_error', optimizer='adam', metrics=['accuracy'])

model.fit(t, y_train, epochs=20, batch_size=10,  verbose=1,validation_data=(t2, y_test))



#Second try with poly kernel




pca = PCA(n_components=100)

x_train=pca.fit_transform(x_train)
x_test= pca.transform(x_test)


kmean= KMeans(n_clusters=10)
kmean.fit(x_train,y_train)


cent= kmean.predict(kmean.cluster_centers_)
cent = np_utils.to_categorical(cent,10)


gp_kernel = PairwiseKernel(gamma=1, metric= 'poly')
gp = GaussianProcessRegressor(kernel=gp_kernel )

gp.fit(kmean.cluster_centers_,cent)



t = gp.predict(x_train)
t2 = gp.predict(x_test)


model = Sequential()
model.add(Dense(20, input_dim=10, activation='relu'))
model.add(Dense(10,  activation='sigmoid'))
model.compile( loss=  'mean_squared_error', optimizer='adam', metrics=['accuracy'])

model.fit(t, y_train, epochs=20, batch_size=10,  verbose=1,validation_data=(t2, y_test))


#Third try with polynomial kernel


pca = PCA(n_components=100)

x_train=pca.fit_transform(x_train)
x_test= pca.transform(x_test)


kmean= KMeans(n_clusters=10)
kmean.fit(x_train,y_train)


cent= kmean.predict(kmean.cluster_centers_)
cent = np_utils.to_categorical(cent,10)

gp_kernel = PairwiseKernel(gamma=1, metric= 'polynomial')
gp = GaussianProcessRegressor(kernel=gp_kernel )
gp.fit(kmean.cluster_centers_,cent)



t = gp.predict(x_train)
t2 = gp.predict(x_test)

model = Sequential()
model.add(Dense(20, input_dim=10, activation='relu'))
model.add(Dense(10,  activation='sigmoid'))
model.compile( loss=  'mean_squared_error', optimizer='adam', metrics=['accuracy'])

model.fit(t, y_train, epochs=20, batch_size=10,  verbose=1,validation_data=(t2, y_test))



#Forth try without pca 



kmean= KMeans(n_clusters=10)
kmean.fit(x_train,y_train)


cent= kmean.predict(kmean.cluster_centers_)
cent = np_utils.to_categorical(cent,10)

gp_kernel = PairwiseKernel(gamma=1, metric= 'linear')
gp = GaussianProcessRegressor(kernel=gp_kernel )
gp.fit(kmean.cluster_centers_,cent)



t = gp.predict(x_train)
t2 = gp.predict(x_test)

model = Sequential()
model.add(Dense(20, input_dim=10, activation='relu'))
model.add(Dense(10,  activation='sigmoid'))
model.compile( loss=  'mean_squared_error', optimizer='adam', metrics=['accuracy'])

model.fit(t, y_train, epochs=20, batch_size=10,  verbose=1,validation_data=(t2, y_test))


t = rbf_kernel(x_train,kmean.cluster_centers_  )
t2 = rbf_kernel(x_test,kmean.cluster_centers_  )


model = Sequential()
model.add(Dense(1,input_dim=2, activation='sigmoid'))
model.compile( loss=  'mean_squared_error', optimizer='adam', metrics=['accuracy'])

model.fit(t, y_train, epochs=20, batch_size=10,  verbose=1,validation_data=(t2, y_test))



#Fifth try with multiple hidden layers 




pca = PCA(n_components=100)

x_train=pca.fit_transform(x_train)
x_test= pca.transform(x_test)


kmean= KMeans(n_clusters=10)
kmean.fit(x_train,y_train)


cent= kmean.predict(kmean.cluster_centers_)
cent = np_utils.to_categorical(cent,10)


gp_kernel = PairwiseKernel(gamma=1, metric= 'linear')
gp = GaussianProcessRegressor(kernel=gp_kernel )
gp.fit(kmean.cluster_centers_,cent)




t = gp.predict(x_train)
t2 = gp.predict(x_test)


model = Sequential()
model.add(Dense(200, input_dim=10, activation='relu'))
model.add(Dense(200,  activation='relu'))
model.add(Dense(200,  activation='relu'))
model.add(Dense(10, activation='sigmoid'))
model.compile( loss=  'mean_squared_error', optimizer='adam', metrics=['accuracy'])

model.fit(t, y_train, epochs=20, batch_size=10,  verbose=1,validation_data=(t2, y_test))




