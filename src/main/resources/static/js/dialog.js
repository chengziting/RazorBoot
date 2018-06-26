/**
 * Created by user on 2018/5/14.
 */
function showMessageDialog(message,callBack) {
    var msgDlg = $("#messageBoxDialog");
    msgDlg.find("p").text(message)
    msgDlg.dialog({
        closeOnEscape: false,
        open: function(event, ui) {
            $(".ui-dialog-titlebar-close", ui.dialog | ui).hide();
        },
        height:200,
        width:300,
        modal:true,
        buttons:[{
            text:'Ok',
            click:function () {
                $( this ).dialog( "close" );
                if(callBack != null || callBack != undefined) {
                    callBack();
                }
            }
        }]
    }).dialog("open");
}

function showConfirmDialog(title,message,callBack) {
    var cfmDlg = $("#confirmBoxDialog");
    cfmDlg.attr("title",title);
    cfmDlg.find("p").text(message);
    cfmDlg.dialog({
        closeOnEscape: false,
        open: function(event, ui) {
            $(".ui-dialog-titlebar-close", ui.dialog | ui).hide();
        },
        height:200,
        width:300,
        modal:true,
        buttons:[{
            text:'Ok',
            click:function () {
                $( this ).dialog( "close" );
                callBack(1);
            }
        },{
            text:'Cancel',
            click:function () {
                $( this ).dialog( "close" );
                callBack(0);
            }
        }]
    }).dialog("open");
}

function showTargetDialog(id,title,callBack) {
    var targetDlg = $("#"+id);
    targetDlg.attr("title",title);
    targetDlg.dialog({
        closeOnEscape: false,
        open: function(event, ui) {
            $(".ui-dialog-titlebar-close", ui.dialog | ui).hide();
        },
        height:500,
        width:800,
        modal:true,
        buttons:[{
            text:'Ok',
            click:function () {
                $( this ).dialog( "close" );
                callBack(1);
            }
        },{
            text:'Cancel',
            click:function () {
                $( this ).dialog( "close" );
                callBack(0);
            }
        }]
    }).dialog("open");
}

function closeDialog(dialogId) {
    $("#"+dialogId).dialog("close");
}
