# Awtad — Development Roadmap

This roadmap divides Awtad into testable and reviewable development phases.

Each major phase should be developed on a separate Git branch and reviewed through a Pull Request.

## Phase 0 — Product Foundation

**Branch:** `docs/product-foundation`

- Define the product vision.
- Define MVP and future scope.
- Document privacy rules.
- Document system architecture.
- Define the technology stack.
- Create the initial roadmap.
- Configure `.gitignore`.
- Improve the root README.

**Result:** The project is clearly defined before implementation begins.

## Phase 1 — Backend Foundation

**Suggested branch:** `backend/project-foundation`

- Generate the Spring Boot project.
- Configure Java 21 and Maven.
- Add PostgreSQL configuration.
- Add environment-based secrets.
- Configure Flyway.
- Add a health-check endpoint.
- Add consistent API error responses.
- Add OpenAPI/Swagger.
- Create the first unit and integration tests.

**Result:** A tested backend starts successfully and connects to PostgreSQL.

## Phase 2 — Authentication and Pairing

**Suggested branches:**

- `backend/authentication`
- `backend/companion-pairing`

Features:

- User registration.
- Login.
- Password hashing.
- JWT access and refresh tokens.
- Logout and token revocation.
- Invitation-code generation.
- Companion invitation acceptance.
- One active companion relationship per user.
- Authorization and ownership checks.

**Result:** Two users can securely create accounts and become companions.

## Phase 3 — Shared Routine and Daily Progress

**Suggested branches:**

- `backend/shared-routine`
- `backend/daily-progress`
- `backend/streak-engine`

Features:

- Create and update a shared routine.
- Required and optional tasks.
- Task display order.
- Complete and undo task completion.
- Idempotent completion requests.
- Personal daily progress.
- Companion-visible progress.
- Personal streak calculation.
- Companion streak calculation.
- Streak freeze rules.
- XP and coin transactions.

**Result:** The backend supports the complete daily Awtad workflow.

## Phase 4 — Android Foundation

**Suggested branch:** `android/project-foundation`

- Generate the Java and XML Android project.
- Configure Material Components.
- Configure Navigation Component.
- Establish MVVM packages.
- Configure Retrofit and OkHttp.
- Configure Room.
- Configure Hilt.
- Create the application design system.
- Add Arabic RTL support.
- Create loading, empty, error, and offline states.

**Result:** The Android foundation is ready for real features.

## Phase 5 — Authentication and Companion Setup UI

**Suggested branches:**

- `android/authentication`
- `android/companion-pairing`

Screens:

- Splash screen.
- Onboarding.
- Registration.
- Login.
- Generate invitation code.
- Enter invitation code.
- Companion confirmation.

**Result:** Two Android users can register, log in, and connect.

## Phase 6 — Daily Journey Experience

**Suggested branches:**

- `android/daily-routine`
- `android/journey-map`
- `android/companion-progress`

Features:

- Today’s shared routine.
- Required and optional task states.
- Two parallel journey paths.
- User and companion characters.
- Animated character movement.
- Daily completion celebration.
- Personal and companion streak display.
- XP and coin feedback.
- Daily history.

**Result:** The main game experience is functional.

## Phase 7 — Offline, Notifications, and Gamification

**Suggested branches:**

- `android/offline-sync`
- `notifications/firebase`
- `gamification/rewards`

Features:

- Offline task completion.
- Pending-operation synchronization.
- Duplicate prevention.
- Local reminders.
- Firebase Cloud Messaging.
- Quiet hours.
- Companion progress notifications.
- Streak freezes.
- Achievements.
- Weekly cooperative challenges.
- Cosmetic rewards.

**Result:** Awtad is reliable and engaging during daily use.

## Phase 8 — Quality and Security

**Suggested branches:**

- `quality/automated-tests`
- `quality/security-hardening`
- `quality/accessibility`

- Backend unit and integration tests.
- Android unit and UI tests.
- Security and authorization tests.
- Input-validation review.
- Accessibility review.
- RTL layout review.
- Performance testing.
- Offline and synchronization testing.
- Secret scanning.
- GitHub Actions.

**Result:** The application is safe, maintainable, and tested.

## Phase 9 — Deployment and Portfolio Release

**Suggested branch:** `release/1.0.0`

- Deploy the backend using HTTPS.
- Deploy PostgreSQL securely.
- Configure production Firebase.
- Create a signed Android build.
- Add screenshots and architecture diagrams.
- Add a demo video.
- Complete API documentation.
- Complete the GitHub README.
- Publish the first GitHub release.
- Prepare CV and portfolio descriptions.

**Result:** Awtad is ready to demonstrate as a complete full-stack portfolio project.

## Working Rules

- Do not commit directly to `main`.
- Use one focused branch per feature.
- Write clear English commit messages.
- Never commit secrets.
- Review `git status` and `git diff` before every commit.
- Test each feature before opening a Pull Request.
- Keep Pull Requests focused and reasonably small.
- Update documentation whenever product behavior changes.