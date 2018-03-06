from keras.datasets import mnist
from sklearn import preprocessing
from sklearn.decomposition import PCA
from sklearn import svm
from sklearn.metrics import accuracy_score
from sklearn.grid_search import GridSearchCV


#Here i read the dataset
(X_train, y_train), (X_test, y_test) = mnist.load_data()


x_train = X_train.reshape(60000, 28 * 28)
x_test = X_test.reshape(10000, 28 * 28)
#Here i keep only 10000 samples 
x_train= x_train[0:10000,]
y_train= y_train[0:10000,]

x_test= x_test[0:2000,]
y_test= y_test[0:2000,]

scaler = preprocessing.MinMaxScaler()
scaler.fit(x_train)
x_train = scaler.transform(x_train)
x_test = scaler.transform(x_test)

y_train = y_train % 2
y_test = y_test % 2

#First try with 10000 


clf = svm.SVC(C=10, gamma=0.01, kernel='rbf')
clf.fit(x_train, y_train)
x= clf.predict(x_test)
print (accuracy_score(x, y_test))

#Second try with poly kernel and degree=3


clf = svm.SVC(C=10, gamma=0.01, kernel='poly', degree= 3)
clf.fit(x_train, y_train)
x= clf.predict(x_test)
print (accuracy_score(x, y_test))

#Third try with linear kerner

clf = svm.SVC(C=10, gamma=0.01, kernel='linear')
clf.fit(x_train, y_train)
x= clf.predict(x_test)
print (accuracy_score(x, y_test))


#Forth try with gamma=0.001

clf = svm.SVC(C=10, gamma=0.001, kernel='rbf')
clf.fit(x_train, y_train)
x= clf.predict(x_test)
print (accuracy_score(x, y_test))


#Fifth try with c=50

clf = svm.SVC(C=50, gamma=0.01, kernel='rbf')
clf.fit(x_train, y_train)
x= clf.predict(x_test)
print (accuracy_score(x, y_test))

#Sixth try with c=100

clf = svm.SVC(C=100, gamma=0.01, kernel='rbf')
clf.fit(x_train, y_train)
x= clf.predict(x_test)
print (accuracy_score(x, y_test))
 
#Seventh try with c=100 and gamma=0.001

clf = svm.SVC(C=100, gamma=0.001, kernel='linear')
clf.fit(x_train, y_train)
x= clf.predict(x_test)
print (accuracy_score(x, y_test))


#Eighth try with c=1000 and gamma=0.01


 clf = svm.SVC(C=1000, gamma=0.01, kernel='linear')
clf.fit(x_train, y_train)
x= clf.predict(x_test)
print (accuracy_score(x, y_test))

#Ninth try with pca to 100 dimensions

pca = PCA(n_components=100)

x_train=pca.fit_transform(x_train)
x_test= pca.transform(x_test)

clf = svm.SVC(C=10000, gamma=0.01, kernel='linear')
clf.fit(x_train, y_train)
x= clf.predict(x_test)
print (accuracy_score(x, y_test))


#Grid search

k = ['rbf', 'poly']
c = [10, 1000]
g = [0.01, 0.0001]
param_grid = dict(kernel=k, C=c, gamma=g)
svr = svm.SVC()
grid = GridSearchCV(svr, param_grid, scoring='accuracy')
grid.fit(x_train, y_train)
print(grid.best_params)

#Last run with the grid search results


pca = PCA(n_components=100)

x_train=pca.fit_transform(x_train)
x_test= pca.transform(x_test)
clf = svm.SVC(C=150, gamma= 0.01, kernel='rbf')
clf.fit(x_train, y_train)
x= clf.predict(x_test)
print (accuracy_score(x, y_test))
