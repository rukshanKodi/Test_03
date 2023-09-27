// assignTask.js

document.addEventListener('DOMContentLoaded', function() {
    var tasks = [
        { id: 1, name: 'Task 1' },
        { id: 2, name: 'Task 2' },
        { id: 3, name: 'Task 3' }
    ];

    var members = [
        { id: 1, name: 'Member 1' },
        { id: 2, name: 'Member 2' },
        { id: 3, name: 'Member 3' }
    ];

    var taskSelect = document.getElementById('taskId');
    var memberSelect = document.getElementById('memberId');

    tasks.forEach(function(task) {
        var option = document.createElement('option');
        option.value = task.id;
        option.textContent = task.name;
        taskSelect.appendChild(option);
    });

    members.forEach(function(member) {
        var option = document.createElement('option');
        option.value = member.id;
        option.textContent = member.name;
        memberSelect.appendChild(option);
    });
});

document.getElementById('assignTaskForm').addEventListener('submit', function(event) {
    var taskId = document.getElementById('taskId').value;
    var memberId = document.getElementById('memberId').value;

    if (!taskId || !memberId) {
        alert('Please select a task and a household member.');
        event.preventDefault();
    }
});
