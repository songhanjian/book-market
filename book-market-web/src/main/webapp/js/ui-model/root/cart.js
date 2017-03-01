/**
 * Created by shj on 16-10-4.
 */
app.factory('cartSession',function () {
    var cartSession = {};
    var cart = {};

    cart.id = null;
    cart.userId = null;
    cart.isDelete=null;
    cart.cartBookRels =null;
    cartSession.cart= cart;
    cartSession.totalPrice = 0;
    cartSession.setId = function (id) {
        cartSession.cart.id = id;
    }

    cartSession.getId = function () {
        return cartSession.cart.id;
    }

    cartSession.setUserId= function (uesrId) {
        cartSession.cart.userId= uesrId;
    }
    cartSession.getUserId = function () {
        return cartSession.cart.userId;
    }
    cartSession.setCartBookRels = function (cartBookRels) {
        cartSession.cart.cartBookRels =cartBookRels;
    }
    cartSession.getCartBookRels =function () {
        return cartSession.cart.cartBookRels;
    }
    cartSession.setCart =function (cart) {
        cartSession.cart.id = cart.id;
        cartSession.cart.userId = cart.userId;
        cartSession.cart.isDelete = cart.isDelete;
        cartSession.cart.cartBookRels = cart.cartBookRels;
        angular.forEach(cartSession.cart.cartBookRels,function (e) {
            cartSession.totalPrice = cartSession.totalPrice+e.totalPrice;
        })
    }

    cartSession.getTotalPrice = function () {
        return cartSession.totalPrice;
    }

    cartSession.getCart = function () {
        
        return cartSession.cart;
    }
    
    cartSession.copyCartForView =function () {
        var cart = {};
        cart.id = cartSession.cart.id;
        cart.userId = cartSession.cart.userId;
        cart.isDelete= cartSession.cart.isDelete;
        cart.cartBookRels =[];
        angular.forEach(cartSession.cart.cartBookRels,function (e) {
            var temp = {};
            for(attr in e){
                var str = "temp."+attr+"=e."+attr;
            }
            temp.isChosen = false;
            cart.cartBookRels.push(temp);

        })
        return cart;
    }

    cartSession.updateCartBookRel = function (cartBookrel) {
        var flag = false;
        angular.forEach(cartSession.cart.cartBookRels,function (e) {
            if(e.id==cartBookrel.id){
                e.count = cartBookrel.count;
                cartSession.totalPrice = cartSession.totalPrice-e.totalPrice;
                e.totalPrice = cartBookrel.totalPrice;

                flag = true;
            }

        })
        if(!flag){
            cartSession.cart.cartBookRels.push(cartBookrel);
        }
        cartSession.totalPrice = cartSession.totalPrice+cartBookrel.totalPrice;
    }


    cartSession.updateCartBookRels = function (cartBookrels) {
        cartSession.cart.cartBookRels = cartBookrels;
        var totalPriceTemp = 0;
        for(var i=0;i<cartBookrels.length;i++){
            totalPriceTemp += cartBookrels[i].totalPrice;
        }
        cartSession.totalPrice = totalPriceTemp;
    }


    return cartSession;
})