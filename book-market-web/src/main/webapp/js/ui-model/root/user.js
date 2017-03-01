/**
 * Created by shj on 16-9-30.
 */
app.factory('userSession',function () {
    var userFactory = {};
    var user = {};
    user.id = null;
    user.username = null;
    user.password = null;
    user.email = null;
    user.phone = null;
    user.company = null;
    user.address = null;
    user.isDelete = 0;

    userFactory.user = user;


    userFactory.setId = function (id) {
        user.id = id;
    }

    userFactory.setUsername = function (username) {
        user.username = username;
    }
    userFactory.setPassword = function (password) {
        user.password = password;
    }
    userFactory.setEmail = function (email) {

        user.email = email;
    }
    userFactory.setPhone = function (phone) {
        user.phone = phone;
    }
    userFactory.setCompany = function (company) {
        user.company = company;
    }
    userFactory.setAddress = function (address) {
        user.address = address;
    }
    userFactory.isLogin = function () {
        return !(user.username==null);
    }
    
    userFactory.getUser = function () {
            return userFactory.user;
    }


    userFactory.getEmptyUser = function () {
        var user = {};
        user.id = null;
        user.username = null;
        user.password = null;
        user.email = null;
        user.phone = null;
        user.company = null;
        user.address = null;
        return user;
    }

    userFactory.setUser = function (user) {
        if((user!=undefined)||(user!=null)){
            userFactory.user.id = user.id;
            userFactory.user.username = user.username;
            userFactory.user.password = user.password;
            userFactory.user.email = user.email;
            userFactory.user.phone = user.phone;
            userFactory.user.company = user.company;
            userFactory.user.address = user.address;
        }
    }

        userFactory.userClear = function () {
        userFactory.user.id = null;
        userFactory.user.username = null;
        userFactory.user.password = null;
        userFactory.user.email = null;
        userFactory.user.phone = null;
        userFactory.user.company = null;
        userFactory.user.address = null;
        userFactory.user.isDelete = 0;

    }

    
    return userFactory;


})