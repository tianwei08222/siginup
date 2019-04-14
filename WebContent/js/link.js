/**
 * Created by Asimple on 2017/11/22.
 */
//��������
function pop(){
    //�����ھ���
    makeCenter();
    //��ʼ��ʡ���б�
    initProvince();
    //Ĭ�������, ����һ��ʡ�����choosen��ʽ
    $('[province-id="1"]').addClass('choosen');
    //��ʼ����ѧ�б�
    initSchool(1);
}
//���ش���
function hide()
{
    $('#choose-box-wrapper').css("display","none");
}

function initProvince()
{
    //ԭ�ȵ�ʡ���б����
    $('#choose-a-province').html('');
    for(i=0;i<schoolList.length;i++)
    {
        $('#choose-a-province').append('<a class="province-item" province-id="'+schoolList[i].id+'">'+schoolList[i].name+'</a>');
    }
    //���ʡ���б����click�¼�
    $('.province-item').bind('click', function(){
            var item=$(this);
            var province = item.attr('province-id');
            var choosenItem = item.parent().find('.choosen');
            if(choosenItem)
                $(choosenItem).removeClass('choosen');
            item.addClass('choosen');
            //���´�ѧ�б�
            initSchool(province);
        }
    );
}

function initSchool(provinceID)
{
    //ԭ�ȵ�ѧУ�б����
    $('#choose-a-school').html('');
    var schools = schoolList[provinceID-1].school;
    for(i=0;i<schools.length;i++)
    {
        $('#choose-a-school').append('<a class="school-item" school-id="'+schools[i].id+'">'+schools[i].name+'</a>');
    }
    //��Ӵ�ѧ�б����click�¼�
    $('.school-item').bind('click', function(){
            var item=$(this);
            var school = item.attr('school-id');
            //����ѡ���ѧ�ı����е�ֵ
            $('#school-name').val(item.text());
            //�رյ���
            hide();
        }
    );
}

function makeCenter()
{
    $('#choose-box-wrapper').css("display","block");
    $('#choose-box-wrapper').css("position","absolute");
    $('#choose-box-wrapper').css("top", Math.max(0, (($(window).height() - $('#choose-box-wrapper').outerHeight()) / 2) + $(window).scrollTop()) + "px");
    $('#choose-box-wrapper').css("left", Math.max(0, (($(window).width() - $('#choose-box-wrapper').outerWidth()) / 2) + $(window).scrollLeft()) + "px");
}