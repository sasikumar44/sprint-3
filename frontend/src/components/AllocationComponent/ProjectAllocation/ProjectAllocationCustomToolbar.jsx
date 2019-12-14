import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import Tooltip from "@material-ui/core/Tooltip";
import Fab from "@material-ui/core/Fab";
import { Link } from "react-router-dom";
import AllocationIcon from "@material-ui/icons/DoubleArrow";

const useStyles = makeStyles(theme => ({
  root: {
    width: "100%",
    marginTop: theme.spacing(3),
    overflowX: "auto"
  },
  fab: {
    marginLeft: theme.spacing(1)
  }
}));

export default function ProjectAllocationCustomToolbar() {
  const classes = useStyles();

  return (
    <React.Fragment>
      <Tooltip title={"Allocate"}>
        <Fab
          color="primary"
          aria-label="add"
          className={classes.fab}
          size="small"
          component={Link}
          to={"/allocation/manage-project-allocation/allocate"}
        >
          <AllocationIcon />
        </Fab>
      </Tooltip>
    </React.Fragment>
  );
}
