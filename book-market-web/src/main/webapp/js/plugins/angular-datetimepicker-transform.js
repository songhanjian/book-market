(function($) {
    angular.module('ngDatetimepicker', [])
        .constant('dtConfig', {
            format: 'Y/m/d H:i',
            mask:'9999/19/39 29:59'
        })
        .directive("datetimepicker", ['dtConfig','$compile','$filter', function (dtConfig,$compile,$filter) {
            return {
                restrict: "A",   //指令作用范围是element或attribute
                require: "ngModel",  //控制器是指令标签对应的ngModel
                scope: {
                    close: '&',
                    minDate:'=',
                    maxDate:'=',
                    options:"=",
                    id:'@forId'
                },

                link: function (scope, element, attrs, ctrl) {
                    if(!ctrl) return;
                    var ready=false;
                    var idSelector="#"+scope.id;
                    var topH = 0, topM = 0, topS = 0;
                    //var history='';
                    var title = $("<div>").addClass("prev-btns title").append($("<div class='xdsoft_prev'>h</div><div class='xdsoft_prev'>m</div><div class='xdsoft_prev'>s</div>"));
                    var prevs = $("<div>").addClass("prev-btns").append($("<div class='xdsoft_prev p1'></div><div class='xdsoft_prev p2'></div><div class='xdsoft_prev p3'></div>"))
                    var nexts = $("<div>").addClass("next-btns").append($("<div class='xdsoft_next n1'></div><div class='xdsoft_next n2'></div><div class='xdsoft_next n3'></div>"))
                    var times = $('<div class="xdsoft_time_box xdsoft_scroller_box"></div>')
                    var hour = $('<div class="xdsoft_time_variant hour"></div>').css({
                        "width": '18px',
                        "float": "left"
                    });
                    var minute = $('<div class="xdsoft_time_variant minute"></div>').css({
                        "width": '18px',
                        "float": "left"
                    });
                    var second = $('<div class="xdsoft_time_variant second"></div>').css({
                        "width": '18px',
                        "float": "left"
                    });
                    for (var i = 0; i < 60; i++) {
                        if (i < 24) hour.append('<div class="xdsoft_time">' + (i < 10 ? ('0' + i) : i) + '</div>');
                        minute.append('<div class="xdsoft_time">' + (i < 10 ? ('0' + i) : i) + '</div>');
                        second.append('<div class="xdsoft_time">' + (i < 10 ? ('0' + i) : i) + '</div>');
                    }
                    times.append(hour, minute, second);
                    function getHeight(selector){
                        return Math.abs(parseInt(selector.css('height'),10))
                    }
                    function n(selector,top,that){
                        var height=getHeight($(selector));
                        var parentHeight=getHeight($(selector).parent())-1
                        top = top||Math.abs(parseInt($(that).css('marginTop'), 10));
                        $(selector).css('margin-top', top=top>parentHeight-height?top-parentHeight:parentHeight-height);
                        return top;
                    }
                    function p(selector,top,that){
                        var height=getHeight($(selector));
                        var parentHeight=getHeight($(selector).parent())-1
                        top = top||Math.abs(parseInt($(that).css('marginTop'), 10));
                        $(selector).css('margin-top', top=top>-parentHeight?0:top+parentHeight);
                        return top;
                    }
                    function bindEvent(id) {
                        $(id + ' .n1').on('click', function () {
                            topH = n(id + ' .hour', topH, this);
                        })
                        $(id + ' .p1').on('click', function () {
                            topH = p(id + ' .hour', topH, this);
                        })

                        $(id + ' .n2').on('click', function () {
                            topM = n(id + ' .minute', topM, this);
                        })
                        $(id + ' .p2').on('click', function () {
                            topM = p(id + ' .minute', topM, this);
                        })

                        $(id + ' .n3').on('click', function () {
                            topS = n(id + ' .second', topS, this);
                        })
                        $(id + ' .p3').on('click', function () {
                            topS = p(id + ' .second', topS, this);
                        })
                        //时分秒选择
                        $(id + ' .hour').off('click').on("click", function (event) {
                            selectTime(event, /\s\d{2}:/, ' ' + $(event.target).text() + ':')
                        })
                        $(id + ' .minute').off('click').on("click", function (event) {
                            selectTime(event, /:\d{2}:/, ':' + $(event.target).text() + ':')
                        })
                        $(id + ' .second').off('click').on("click", function (event) {
                            selectTime(event, /:\d{2}$/, ':' + $(event.target).text());
                        })
                    }
                    //时间选择
                    var selectTime=function(event,o,r){
                        if(!ctrl.$modelValue) {
                            $(element).val($filter('date')(new Date(),'yyyy/MM/dd hh:mm:ss'))
                        }
                        $(event.target).siblings().removeClass('xdsoft_current');
                        $(event.target).addClass('xdsoft_current');
                        $(element).val($(element).val().replace(o,r));
                        $(element).change();
                    }
                    //var initTime=function(type){
                    //    var hms, h, m, s;
                    //    var top=parseInt($('#'+scope.id + ' .'+type).css('margin-top'),10);
                    //    var val=$("input[for-id="+scope.id+"]").val();
                    //    if(val&&val.split(' ').length>1&&(hms=val.split(' ')[1].split(':')).length==3){
                    //        h=hms[0],m=hms[1],s=hms[2];
                    //        var height=getHeight($('#'+scope.id + ' .'+type));
                    //        var parentHeight=getHeight($('#'+scope.id + ' .'+type).parent())-1
                    //        var offsetTop=$('#'+scope.id + ' .'+type+' .xdsoft_time:contains("'+h+'")')[0].offsetTop;
                    //        var realH=offsetTop;
                    //        $('#'+scope.id + ' .'+type+' .xdsoft_time').removeClass('xdsoft_current');
                    //        $('#'+scope.id + ' .'+type+' .xdsoft_time:contains("'+(type=='hour'?h:type=='minute'?m:s)+'")').addClass('xdsoft_current');
                    //        $('#'+scope.id + ' .'+type).css('margin-top',top=top-realH>parentHeight-height?top-realH:parentHeight-height);
                    //    }
                    //    return top;
                    //}
                    var execute=function(){
                        if(scope.minDate) scope.options.minDate=scope.minDate;
                        if(scope.maxDate) scope.options.maxDate=scope.maxDate;
                        scope.options.onGenerate = function () {
                            if(!ready) {
                                $('.xdsoft_datetimepicker:visible').attr('id', scope.id);
                                $(idSelector+' .xdsoft_timepicker>*').remove();
                                $(idSelector+' .xdsoft_timepicker').prepend(prevs).prepend(title).append(times).append(nexts);
                                ready = true;
                                //绑定事件
                                bindEvent(idSelector);
                            }
                        }
                        $(element).datetimepicker(scope.options);
                    }
                    $(element).on('change', function () {          //注册onChange事件，设置viewValue
                        //if($(element).val()&&$(element).val()!==history) {
                        //    topH = initTime('hour');
                        //    topM = initTime('minute');
                        //    topS = initTime('second');
                        //    history=$(element).val()
                        //}
                        scope.$apply(function () {
                            ctrl.$setViewValue($(element).val());
                        });
                    });
                    $(element).on('click', execute);

                    scope.$watch(ctrl.$modelValue, function (n, o) {
                        if (n && n != o) ctrl.$setViewValue(n);
                    });

                    scope.$watch('minDate',function(){
                        execute();
                    })

                    scope.$watch('maxDate',function(){
                        execute();
                    })
                }
            }
        }]);
})(jQuery);