import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import MUIDataTable from "mui-datatables";
import Container from "@material-ui/core/Container";
import Grid from "@material-ui/core/Grid";
import ProjectEmployeeCustomToolbarSelect from "./ProjectEmployeeCustomToolbarSelect";
import Button from "@material-ui/core/Button";
import { Link } from "react-router-dom";

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
  },
  button: {
    marginBottom: theme.spacing(1),
    width: "120px"
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
    name: "Allocated",
    label: "Allocated",
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
    Allocated: "Yes"
  },
  {
    EmployeeId: "E-101",
    Name: "Sankar",
    Designation: "QA",
    Allocated: "No"
  }
];

const options = {
  filterType: "checkbox",
  selectableRowsOnClick: true,
  responsive: "scrollMaxHeight",
  customToolbarSelect: () => {
    return <ProjectEmployeeCustomToolbarSelect />;
  }
};

export default function ProjectEmployee() {
  const classes = useStyles();

  return (
    <div>
      <Container className={classes.container}>
        <Grid container justify="flex-start">
          <Button
            type="submit"
            className={classes.button}
            variant="contained"
            color="primary"
            component={Link}
            to={"/allocation/manage-project-allocation"}
          >
            Back
          </Button>
        </Grid>
        <MUIDataTable data={data} columns={columns} options={options} />
      </Container>
    </div>
  );
}
