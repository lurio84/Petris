import React from "react";
import { Route, Routes } from "react-router-dom";
import jwt_decode from "jwt-decode";
import { ErrorBoundary } from "react-error-boundary";
import AppNavbar from "./util/components/AppNavbar";
import Home from "./pages/home";
import Dashboard from "./pages/home/dashboard";
import PrivateRoute from "./privateRoute";
import Register from "./pages/auth/register";
import Login from "./pages/auth/login";
import Logout from "./pages/auth/logout";
import PlanList from "./public/plan";
import tokenService from "./util/services/token.service";
import UserListAdmin from "./pages/admin/users/UserListAdmin";
import UserEditAdmin from "./pages/admin/users/UserEditAdmin";
import SwaggerDocs from "./public/swagger";
import DeveloperList from "./views/developers";
import AchievementListAdmin from "./pages/admin/achievement/achievementListAdmin";
import AchievementEditAdmin from "./pages/admin/achievement/achievementEditAdmin";
import ControlPanel from "./pages/admin/controlPanel";
import AchievementsPage from "./pages/player/achievement/achievementPage";
import Profile from "./pages/player/profile"
import EditPlayer from "./pages/player/editPlayer"


function ErrorFallback({ error, resetErrorBoundary }) {
  return (
    <div role="alert">
      <p>Something went wrong:</p>
      <pre>{error.message}</pre>
      <button onClick={resetErrorBoundary}>Try again</button>
    </div>
  )
}

function App() {
  const jwt = tokenService.getLocalAccessToken();
  let roles = []
  if (jwt) {
    roles = getRolesFromJWT(jwt);
  }

  function getRolesFromJWT(jwt) {
    return jwt_decode(jwt).authorities;
  }

  let adminRoutes = <></>;
  let ownerRoutes = <></>;
  let userRoutes = <></>;
  let vetRoutes = <></>;
  let publicRoutes = <></>;

  roles.forEach((role) => {
    if (role === "ADMIN") {
      adminRoutes = (
        <>
          <Route path="/controlPanel/users" exact={true} element={<PrivateRoute><UserListAdmin /></PrivateRoute>} />
          <Route path="/controlPanel/users/:username" exact={true} element={<PrivateRoute><UserEditAdmin /></PrivateRoute>} />
          <Route path="/controlPanel" exact={true} element={<PrivateRoute><ControlPanel/></PrivateRoute>} />
          <Route path="/controlPanel/achievements" exact={true} element={<PrivateRoute><AchievementListAdmin/></PrivateRoute>} />
          <Route path="/controlPanel/achievements/:achievementId" exact={true} element={<PrivateRoute><AchievementEditAdmin/></PrivateRoute>} />
        </>)
    }
    if (role === "PLAYER") {
      ownerRoutes = (
        <>
          <Route path="/dashboard" element={<PrivateRoute><Dashboard /></PrivateRoute>} />
          <Route path="/achievements/:playerId" exact={true} element={<PrivateRoute><AchievementsPage/></PrivateRoute>} />
          <Route path="/player/:playerId" exact={true} element={<PrivateRoute><Profile/></PrivateRoute>} />
          <Route path="/player/edit/:playerId" exact={true} element={<PrivateRoute><EditPlayer/></PrivateRoute>} />
        </>)
    }
  })
  if (!jwt) {
    publicRoutes = (
      <>
        <Route path="/register" element={<Register />} />
        <Route path="/login" element={<Login />} />
      </>
    )
  } else {
    userRoutes = (
      <>
        {/* <Route path="/dashboard" element={<PrivateRoute><Dashboard /></PrivateRoute>} /> */}
        <Route path="/developers" element={<DeveloperList />} />
        <Route path="/logout" element={<Logout />} />
        <Route path="/login" element={<Login />} />
      </>
    )
  }

  return (
    <div>
      <ErrorBoundary FallbackComponent={ErrorFallback} >
        <AppNavbar />
        <Routes>
          <Route path="/" exact={true} element={<Home />} />
          <Route path="/plans" element={<PlanList />} />
          <Route path="/docs" element={<SwaggerDocs />} />
          {publicRoutes}
          {userRoutes}
          {adminRoutes}
          {ownerRoutes}
          {vetRoutes}
        </Routes>
      </ErrorBoundary>
    </div>
  );
}

export default App;
