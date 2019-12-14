import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import MUIDataTable from "mui-datatables";
import Container from "@material-ui/core/Container";
import ProjectAllocationCustomToolbarSelect from "./ProjectAllocationCustomToolbarSelect";
import ProjectAllocationCustomToolbar from "./ProjectAllocationCustomToolbar";

const useStyles = makeStyles(theme => ({
  root: {
    width: "100%",
    marginTop: theme.spacing(3),
    overflowX: "auto"
  },
  container: {
    marginTop: theme.spacing(3),
    paddingTop: theme.spacing(2),
    paddingBottom: theme.spacing(2)
  }
}));

const columns = [
  {
    name: "EmployeeId",
    label: "Employee Id",
    options: {
      filter: true,
      sort: true
    }
  },
  {
    name: "Name",
    label: "Name",
    options: {
      filter: true,
      sort: false
    }
  },
  {
    name: "Designation",
    label: "Designation",
    options: {
      filter: true,
      sort: false
    }
  },
  {
    name: "Project",
    label: "Project",
    options: {
      filter: true,
      sort: false
    }
  }
];

const data = [
  {
    EmployeeId: "E-100",
    Name: "Bals",
    Designation: "Developer",
    Project: "CMS"
  },
  {
    EmployeeId: "E-101",
    Name: "Sankar",
    Designation: "QA",
    Project: "LMS"
  }
];

const options = {
  filterType: "checkbox",
  selectableRowsOnClick: true,
  responsive: "scrollMaxHeight",
  customToolbar: () => {
    return <ProjectAllocationCustomToolbar />;
  },
  customToolbarSelect: () => {
    return <ProjectAllocationCustomToolbarSelect />;
  }
};

export default function ManageProjectAllocation() {
  const classes = useStyles();
  // const [value, setValue] = React.useState("");

  // const handleChange = event => {
  //   setValue(event.target.value);
  // };

  return (
    <div>
      <Container className={classes.container}>
        <MUIDataTable data={data} columns={columns} options={options} />
      </Container>
    </div>
  );
}
