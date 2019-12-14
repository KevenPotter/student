var studentId = null;
var studentName = null;
var studentDepartmentId = null;
var studentMajorId = null;
var pageIndex = 1; // 默认当前页码
var pageLoadCounts = 0;

/**
 * @description 页面初始化加载事件
 */
$(document).ready(function () {
    pageLoadCounts = 0;
    loadStudentsList(studentId, studentName, studentDepartmentId, studentMajorId, pageIndex);
    loadDepartmentsList();
    loadMajorsList();
    ++pageLoadCounts;
});

/**
 *
 * @param studentId 学生编号
 * @param studentName 学生姓名
 * @param pageIndex 当前页码
 * @author KevenPotter
 * @date 2019-11-25 22:22:03
 * @description 加载学生列表
 */
function loadStudentsList(studentId, studentName, departmentId, majorId, pageIndex) {
    var studentsTableTBody = $('#studentsTableTBody');
    var studentsPage = $('#studentsPage');
    clearHtml(studentsTableTBody);
    clearHtml(studentsPage);
    var requestParam = {"studentId": studentId, "name": studentName, "departmentId": departmentId, "majorId": majorId, "pageNo": pageIndex, "pageSize": 5};
    $.ajax({
        url: studentManagementSystem + "/student/students",
        type: "GET",
        dataType: "json",
        data: requestParam,
        success: function (data) {
            if (data.code == "100002") {
                layer.msg('未搜索出指定信息......', {icon: 6, time: 2000});
                return;
            }
            var studentsArray = data.data.list;
            for (var studentIndex = 0, length = studentsArray.length; studentIndex < length; studentIndex++) {
                var item = studentsArray[studentIndex];
                studentsTableTBody.append('<tr> ' +
                    '<td>' + item.id + '</td>' +
                    '<td>' + item.studentId + '</td>' +
                    '<td>' + item.name + '</td>' +
                    '<td>' + item.sex + '</td>' +
                    '<td id="departmentId_' + studentIndex + '">' + item.departmentId + '</td>' +
                    '<td id="majorId_' + studentIndex + '">' + item.majorId + '</td>' +
                    '<td>' + item.grade + '</td>' +
                    '<td>' + item.clazz + '</td>' +
                    '<td>' + item.address + '</td>' +
                    '</tr>');
                $.ajax({
                    url: studentManagementSystem + "/department/department/" + item.departmentId,
                    type: "GET",
                    dataType: "json",
                    async: false,
                    success: function (data) {
                        $('#departmentId_' + studentIndex + '').html(data.data.departmentName);
                        $.ajax({
                            url: studentManagementSystem + "/major/major/" + item.majorId,
                            type: "GET",
                            dataType: "json",
                            async: false,
                            success: function (data) {
                                $('#majorId_' + studentIndex + '').html(data.data.majorName);
                            }
                        });
                    }
                });
            }
            var pageNum = data.data.pageNum;
            var pages = data.data.pages;
            $('#pageInfo').html('第 <b>' + data.data.pageNum + '</b> 页 第 ' + data.data.startRow + ' 到 ' + data.data.endRow + ' 条记录，共 ' + data.data.total + ' 条');
            studentsPage.bootstrapPaginator({
                bootstrapMajorVersion: 3,
                numberOfPages: 5,
                currentPage: pageNum,
                totalPages: pages,
                onPageClicked: function (event, originalEvent, type, page) {
                    loadStudentsList(studentId, studentName, departmentId, majorId, page);
                }
            });
        }
    });
}

/**
 * @author KevenPotter
 * @date 2019-12-06 16:03:58
 * @description 加载系别列表
 */
function loadDepartmentsList() {
    var departmentsSelect = $('#departmentsSelect');
    $.ajax({
        url: studentManagementSystem + "/department/departments",
        type: "GET",
        dataType: "json",
        success: function (data) {
            var departmentsArray = data.data;
            departmentsSelect.append('<option value="' + null + '">请选择系别</option>');
            for (var departmentIndex = 0, length = departmentsArray.length; departmentIndex < length; departmentIndex++) {
                var item = departmentsArray[departmentIndex];
                departmentsSelect.append('<option id="' + departmentIndex + '" value="' + item.departmentId + '">' + item.departmentName + '</option>');
                bootstrapSelectFlush(departmentsSelect);
            }
        }
    });
}

/**
 * @author KevenPotter
 * @date 2019-12-09 10:46:18
 * @description 加载专业列表
 */
function loadMajorsList() {
    var majorsSelect = $('#majorsSelect');
    clearHtml(majorsSelect);
    var options = $('select option:selected');
    var departmentId = options.val();
    if (undefined == departmentId || null == departmentId || "null" == departmentId) {
        $("#majorsSelect").attr("disabled", true);
        majorsSelect.append('<option value="' + null + '">请选择专业</option>');
        bootstrapSelectFlush(majorsSelect);
        if (pageLoadCounts != 0) {
            search();
        }
        return;
    } else {
        $("#majorsSelect").attr("disabled", false);
    }
    $.ajax({
        url: studentManagementSystem + "/major/majors/" + departmentId,
        type: "GET",
        dataType: "json",
        success: function (data) {
            var majorsArray = data.data;
            majorsSelect.append('<option value="' + null + '">请选择专业</option>');
            for (var majorIndex = 0, length = majorsArray.length; majorIndex < length; majorIndex++) {
                var item = majorsArray[majorIndex];
                majorsSelect.append('<option id="' + majorIndex + '" value="' + item.majorId + '">' + item.majorName + '</option>');
                bootstrapSelectFlush(majorsSelect);
            }
        }
    });
    search();
}

/**
 * @author KevenPotter
 * @date 2019-11-26 08:33:39
 * @description 搜索内容
 */
function search() {
    var studentIdVal = $('#studentId').val();
    var studentNameVal = $('#studentName').val();
    var studentDepartmentIdVal = $('#departmentsSelect option:selected').val();
    var studentMajorIdVal = $('#majorsSelect option:selected').val();
    studentId = studentIdVal ? studentIdVal : null;
    studentName = studentNameVal ? studentNameVal : null;
    studentDepartmentId = studentDepartmentIdVal ? studentDepartmentIdVal : null;
    studentMajorId = studentMajorIdVal ? studentMajorIdVal : null;
    if ("null" == studentId || undefined == studentId) studentId = null;
    if ("null" == studentName || undefined == studentName) studentName = null;
    if ("null" == studentDepartmentId || undefined == studentDepartmentId) studentDepartmentId = null;
    if ("null" == studentMajorId || undefined == studentMajorId) studentMajorId = null;
    loadStudentsList(studentId, studentName, studentDepartmentId, studentMajorId, pageIndex);
}

/**
 * @param element HTML元素
 * @author KevenPotter
 * @date 2019-11-25 22:45:16
 * @description 清空元素的标签内容
 */
function clearHtml(element) {
    element.html("");
}

/**
 * @param element bootstrap-select元素
 * @author KevenPotter
 * @date 2019-12-09 13:40:49
 * @description 重新渲染bootstrap-select元素的内容
 */
function bootstrapSelectFlush(element) {
    element.selectpicker("refresh");
    element.selectpicker("render");
}