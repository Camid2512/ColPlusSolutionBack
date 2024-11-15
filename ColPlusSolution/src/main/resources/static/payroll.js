// Fetch payrolls on page load
document.addEventListener('DOMContentLoaded', function() {
    fetchPayrolls();
});

let payrollsData = []; // Variable global para almacenar los datos de nóminas

function fetchPayrolls(order = 'asc') {
    axios.get('http://localhost:8082/payroll/getall')
        .then(response => {
            payrollsData = response.data;

            // Ordenar datos en función del parámetro "order"
            payrollsData.sort((a, b) => {
                if (order === 'asc') {
                    return a.code - b.code;
                } else {
                    return b.code - a.code;
                }
            });

            // Renderizar tabla
            renderTable(payrollsData);
        })
        .catch(error => {
            console.error("Error fetching payrolls:", error);
            alert("An error occurred while fetching payroll data.");
        });
}

function sortTable(order) {
    fetchPayrolls(order); // Recargar los datos en el orden solicitado
}

function renderTable(payrolls) {
    const tableBody = document.getElementById('payrollTableBody');
    tableBody.innerHTML = '';
    payrolls.forEach(payroll => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${payroll.code}</td>
            <td>${payroll.employeeName}</td>
            <td>${payroll.department}</td>
            <td>${payroll.position}</td>
            <td>${new Date(payroll.hireDate).toLocaleDateString()}</td>
            <td>${payroll.healthInsurance}</td>
            <td>${payroll.occupationalRiskInsurance}</td>
            <td>${payroll.pension}</td>
            <td>${payroll.salary.toFixed(2)}</td>
            <td>
                <a href="#editPayrollModal" class="edit" data-toggle="modal" onclick="openEditModal(${payroll.code})"><i class="material-icons" data-toggle="tooltip" title="Edit"></i></a>
                <a href="#deletePayrollModal" class="delete" data-toggle="modal" onclick="openDeleteModal(${payroll.code})"><i class="material-icons" data-toggle="tooltip" title="Delete"></i></a>
            </td>
        `;
        tableBody.appendChild(row);
    });
}

// Handle form submission for adding payroll
document.getElementById('addPayrollForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent default form submission

    // Get form values
    const code = document.getElementById('addCode').value;
    const employeeName = document.getElementById('addEmployeeName').value;
    const department = document.getElementById('addDepartment').value;
    const position = document.getElementById('addPosition').value;
    const hireDate = document.getElementById('addHireDate').value;
    const healthInsurance = document.getElementById('addHealthInsurance').value;
    const riskInsurance = document.getElementById('addRiskInsurance').value;
    const pension = document.getElementById('addPension').value;
    const salary = document.getElementById('addSalary').value;

    // Validate fields
    if (!code || !employeeName || !department || !position || !hireDate || !healthInsurance || !riskInsurance || !pension || !salary) {
        alert("Please fill in all fields.");
        return;
    }

    // Make API call to create payroll
    axios.post(`http://localhost:8082/payroll/create?code=${code}&employeeName=${employeeName}&department=${department}&position=${position}&hireDate=${hireDate}&healthInsurance=${healthInsurance}&occupationalRiskInsurance=${riskInsurance}&pension=${pension}&salary=${parseFloat(salary)}`)
    .then(response => {
        alert(response.data); // Display success message
        $('#addPayrollModal').modal('hide'); // Hide modal
        fetchPayrolls(); // Refresh payroll list
    })
    .catch(error => {
        if (error.response) {
            alert("Error: " + error.response.data);
        } else {
            console.error("Error creating payroll:", error);
            alert("An error occurred while adding the payroll.");
        }
    });
});

function openEditModal(code) {
    axios.get(`http://localhost:8082/payroll/getbyid/${code}`)
        .then(response => {
            const payroll = response.data;

            // Eliminar el formulario anterior si existe
            const existingForm = document.getElementById('dynamicEditForm');
            if (existingForm) {
                existingForm.remove();
            }

            // Crear un nuevo formulario
            const form = document.createElement('form');
            form.id = 'dynamicEditForm';
            form.innerHTML = `
                <div class="form-group">
                    <label>Code</label>
                    <input type="text" class="form-control" id="editCode" value="${payroll.code}" readonly>
                </div>
                <div class="form-group">
                    <label>Employee Name</label>
                    <input type="text" class="form-control" id="editEmployeeName" value="${payroll.employeeName}" required>
                </div>
                <div class="form-group">
                    <label>Department</label>
                    <input type="text" class="form-control" id="editDepartment" value="${payroll.department}" required>
                </div>
                <div class="form-group">
                    <label>Position</label>
                    <input type="text" class="form-control" id="editPosition" value="${payroll.position}" required>
                </div>
                <div class="form-group">
                    <label>Hire Date</label>
                    <input type="date" class="form-control" id="editHireDate" value="${new Date(payroll.hireDate).toISOString().split('T')[0]}" required>
                </div>
                <div class="form-group">
                    <label>Health Insurance</label>
                    <input type="text" class="form-control" id="editHealthInsurance" value="${payroll.healthInsurance}" required>
                </div>
                <div class="form-group">
                    <label>Risk Insurance</label>
                    <input type="text" class="form-control" id="editRiskInsurance" value="${payroll.occupationalRiskInsurance}" required>
                </div>
                <div class="form-group">
                    <label>Pension</label>
                    <input type="text" class="form-control" id="editPension" value="${payroll.pension}" required>
                </div>
                <div class="form-group">
                    <label>Salary</label>
                    <input type="number" class="form-control" id="editSalary" value="${payroll.salary}" required>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-success">Update</button>
                </div>
            `;

            // Añadir el listener al nuevo formulario para manejar la actualización
            form.addEventListener('submit', function(event) {
                event.preventDefault();
                updatePayroll(code);
            });

            // Insertar el formulario en el modal o en el contenedor correspondiente
            const modalBody = document.querySelector('#editPayrollModal .modal-body');
            modalBody.appendChild(form);

            // Mostrar el modal
            $('#editPayrollModal').modal('show');
        })
        .catch(error => {
            console.error("Error fetching payroll by Code:", error);
            alert("Error retrieving payroll details.");
        });
}

function updatePayroll(code) {
    // Obtener valores del formulario
    const employeeName = document.getElementById('editEmployeeName').value;
    const department = document.getElementById('editDepartment').value;
    const position = document.getElementById('editPosition').value;
    const hireDate = document.getElementById('editHireDate').value;
    const healthInsurance = document.getElementById('editHealthInsurance').value;
    const riskInsurance = document.getElementById('editRiskInsurance').value;
    const pension = document.getElementById('editPension').value;
    const salary = parseFloat(document.getElementById('editSalary').value);

    // Enviar los datos actualizados a la API
    axios.put(`http://localhost:8082/payroll/update`, null, {
        params: {
            code,
            employeeName,
            department,
            position,
            hireDate,
            healthInsurance,
            occupationalRiskInsurance: riskInsurance,
            pension,
            salary
        }
    })
    .then(response => {
        alert(response.data); // Mostrar mensaje de éxito
        $('#editPayrollModal').modal('hide'); // Cerrar el modal
        fetchPayrolls(); // Refrescar la lista de nóminas
    })
    .catch(error => {
        console.error("Error updating payroll:", error);
        alert("Error updating payroll.");
    });
}

let employeeToDelete = null; // Variable global para almacenar el código del empleado a eliminar

function openDeleteModal(code) {
    employeeToDelete = code; // Guarda el código del empleado a eliminar
    $('#deleteEmployeeModal').modal('show'); // Muestra el modal de confirmación
}

function confirmDeleteEmployee() {
    if (employeeToDelete) {
        axios.delete(`http://localhost:8082/payroll/deletebyid/${employeeToDelete}`)
            .then(response => {
                alert(response.data);
                $('#deleteEmployeeModal').modal('hide'); // Oculta el modal
                fetchPayrolls(); // Actualiza la tabla
            })
            .catch(error => {
                console.error("Error deleting payroll:", error);
                alert("Error deleting payroll.");
            });
    }
}


function searchEmployee() {
    const code = document.getElementById('searchCode').value.trim();

    if (!code) {
        alert("Please enter a code to search.");
        return;
    }

    axios.get(`http://localhost:8082/payroll/getbyid/${code}`)
        .then(response => {
            const payroll = response.data;
            
            // Limpiar el cuerpo de la tabla y mostrar solo el empleado encontrado
            const tableBody = document.getElementById('payrollTableBody');
            tableBody.innerHTML = '';

            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${payroll.code}</td>
                <td>${payroll.employeeName}</td>
                <td>${payroll.department}</td>
                <td>${payroll.position}</td>
                <td>${new Date(payroll.hireDate).toLocaleDateString()}</td>
                <td>${payroll.healthInsurance}</td>
                <td>${payroll.occupationalRiskInsurance}</td>
                <td>${payroll.pension}</td>
                <td>${payroll.salary.toFixed(2)}</td>
                <td>
                    <a href="#editPayrollModal" class="edit" data-toggle="modal" onclick="openEditModal(${payroll.code})"><i class="material-icons" data-toggle="tooltip" title="Edit"></i></a>
                    <a href="#deletePayrollModal" class="delete" data-toggle="modal" onclick="openDeleteModal(${payroll.code})"><i class="material-icons" data-toggle="tooltip" title="Delete"></i></a>
                </td>
            `;
            tableBody.appendChild(row);
        })
        .catch(error => {
            console.error("Error fetching employee by code:", error);
            alert("Employee not found.");
        });
}

//----------------------------------------

function uploadExcel() {
    const fileInput = document.getElementById("excelFile");

    // Verificar si se ha seleccionado un archivo
    if (fileInput.files.length === 0) {
        alert("Please select an Excel file to upload.");
        return;
    }

    const file = fileInput.files[0];
    const formData = new FormData();
    formData.append("file", file);

    // Llamada al backend para subir el archivo y crear las nóminas
    axios.post("http://localhost:8082/payroll/createwithexcel", formData, {
        headers: {
            "Content-Type": "multipart/form-data"
        }
    })
    .then(response => {
        alert("Payrolls created successfully from Excel file!");
        fetchPayrolls(); // Recargar la lista de nóminas
    })
    .catch(error => {
        console.error("Error uploading Excel file:", error);
        alert("There was an error uploading the file or creating payrolls.");
    });
}


function generatePDF() {
    const { jsPDF } = window.jspdf;
    const doc = new jsPDF();

    doc.text("Employee Payroll List", 14, 10);

    const headers = [["Code", "Employee Name", "Department", "Position", "Hire Date", "Health Insurance", "Risk Insurance", "Pension", "Salary"]];
    const data = payrollsData.map(payroll => [
        payroll.code,
        payroll.employeeName,
        payroll.department,
        payroll.position,
        new Date(payroll.hireDate).toLocaleDateString(),
        payroll.healthInsurance,
        payroll.occupationalRiskInsurance,
        payroll.pension,
        payroll.salary.toFixed(2)
    ]);

    doc.autoTable({
        head: headers,
        body: data,
        startY: 20,
        theme: 'striped'
    });

    doc.save("Employee_Payroll_List.pdf");
}

function renderCharts() {
    // Gráfico de Torta: Cantidad de empleados por departamento
    const departments = payrollsData.reduce((acc, payroll) => {
        acc[payroll.department] = (acc[payroll.department] || 0) + 1;
        return acc;
    }, {});

    const departmentPieChart = new Chart(document.getElementById('departmentPieChart'), {
        type: 'pie',
        data: {
            labels: Object.keys(departments),
            datasets: [{
                data: Object.values(departments),
                backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0']
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    position: 'top',
                }
            }
        }
    });

    // Gráfico de Barras: Cantidad de empleados por posición
    const positions = payrollsData.reduce((acc, payroll) => {
        acc[payroll.position] = (acc[payroll.position] || 0) + 1;
        return acc;
    }, {});

    const positionBarChart = new Chart(document.getElementById('positionBarChart'), {
        type: 'bar',
        data: {
            labels: Object.keys(positions),
            datasets: [{
                label: 'Number of Employees',
                data: Object.values(positions),
                backgroundColor: '#36A2EB'
            }]
        },
        options: {
            responsive: true,
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}

// Llama a renderCharts después de cargar los datos
fetchPayrolls();
renderCharts();

function generatePositionPDF() {
    const { jsPDF } = window.jspdf;
    const doc = new jsPDF("landscape", "mm", "a3");

    doc.text("Employee Count by Position", 14, 10);

    const positions = payrollsData.reduce((acc, payroll) => {
        acc[payroll.position] = (acc[payroll.position] || 0) + 1;
        return acc;
    }, {});

    const positionNames = Object.keys(positions);
    const positionCounts = Object.values(positions);

    let startY = 20;
    positionNames.forEach((position, index) => {
        const barWidth = positionCounts[index] * 10;
        const barColor = `#${Math.floor(Math.random()*16777215).toString(16)}`;

        doc.setFillColor(barColor);
        doc.rect(20, startY, barWidth, 10, "F");
        doc.text(`${position} (${positionCounts[index]})`, barWidth + 25, startY + 7);

        startY += 15;
    });

    doc.save("Position_Bar_Chart.pdf");
}

function generateDepartmentPDF() {
    const { jsPDF } = window.jspdf;
    const doc = new jsPDF();

    // Título del PDF
    doc.text("Employee Count by Department", 14, 10);

    // Obtener los datos de departamentos y contar empleados
    const departments = payrollsData.reduce((acc, payroll) => {
        acc[payroll.department] = (acc[payroll.department] || 0) + 1;
        return acc;
    }, {});

    const departmentNames = Object.keys(departments);
    const departmentCounts = Object.values(departments);
    const totalEmployees = departmentCounts.reduce((sum, count) => sum + count, 0);

    // Configuración del gráfico circular
    const centerX = 105;
    const centerY = 80;
    const radius = 60;
    let startAngle = 0;

    // Colores fijos para cada departamento
    const colors = [
        "#4B0082", "#FF00FF", "#40E0D0", "#FFD700", "#FF4500", "#7FFF00", "#1E90FF", "#FF1493", "#8B4513", "#2E8B57",
        "#4682B4", "#D2691E", "#556B2F", "#8A2BE2", "#FF6347", "#00CED1", "#00FA9A", "#ADFF2F", "#6A5ACD", "#FF7F50",
        "#20B2AA", "#DC143C", "#32CD32", "#8B0000", "#00008B", "#BDB76B", "#A52A2A", "#5F9EA0", "#9932CC", "#FF69B4"
    ]; // Ajusta los colores según prefieras

    // Dibujar los segmentos del gráfico circular
    departmentCounts.forEach((count, index) => {
        const sliceAngle = (count / totalEmployees) * 2 * Math.PI;
        const endAngle = startAngle + sliceAngle;

        // Definir el color de cada segmento
        doc.setFillColor(colors[index % colors.length]);

        // Dibujar el sector en pequeños segmentos usando un bucle
        let angle = startAngle;
        const step = 0.01; // Define el paso para aproximar el arco
        doc.moveTo(centerX, centerY);
        while (angle <= endAngle) {
            const x = centerX + radius * Math.cos(angle);
            const y = centerY + radius * Math.sin(angle);
            doc.lineTo(x, y);
            angle += step;
        }
        doc.lineTo(centerX, centerY); // Cerrar el sector
        doc.fill();

        // Actualizar el ángulo de inicio para el siguiente sector
        startAngle = endAngle;
    });

    // Dibujar el contorno del gráfico circular
    doc.setDrawColor(0, 0, 0); // Color de borde (negro)
    doc.circle(centerX, centerY, radius); // Dibuja el círculo completo como contorno

    // Leyenda
    let legendY = 160;
    departmentNames.forEach((dept, index) => {
        doc.setFillColor(colors[index % colors.length]);
        doc.rect(20, legendY, 5, 5, "F");
        doc.text(`${dept} (${departments[dept]})`, 30, legendY + 4);
        legendY += 10;
    });

    // Guardar el PDF
    doc.save("Department_Pie_Chart.pdf");
}