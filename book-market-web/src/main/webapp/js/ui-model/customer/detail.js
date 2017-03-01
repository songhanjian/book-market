/**
 * Created by shj on 16-10-2.
 */
app.factory('detailSession',function () {
    var detailSession = {};
    detailSession.bookId = null;

    detailSession.setBookId = function (param) {
        detailSession.bookId = param;
    }

    detailSession.getBookId = function () {
        return detailSession.bookId;
    }


    return detailSession;
})