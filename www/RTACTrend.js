var exec = require('cordova/exec');
var RTACTrend={
    printTrendImage:function(fnSuccess, fnError,imageString){
        exec(fnSuccess, fnError, "RTACTrend", "printBitmap",[imageString]);
    }
}
module.exports = RTACTrend;