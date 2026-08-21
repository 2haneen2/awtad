# Awtad — Product Requirements

**Status:** Draft  
**Version:** 0.1  
**Platform:** Android  
**Primary language:** Arabic (RTL)

## 1. Product Overview

Awtad is a gamified spiritual-routine application that helps two friends remain consistent with their daily routines.

Instead of presenting progress as a traditional checklist, Awtad transforms the daily routine into a shared journey. Each friend has a character that moves along a parallel path as tasks are completed.

The first version focuses on pairs. The system will be designed so that group support can be added in a future release.

## 2. Problem Statement

Tracking daily spiritual routines through messaging groups is inconvenient because:

- Daily progress becomes mixed with normal messages.
- There is no clear history or streak tracking.
- Progress is presented as plain text rather than an engaging experience.
- Reminders are manual and inconsistent.
- There is no structured shared plan or reward system.

Awtad provides a focused, motivating, and privacy-aware alternative.

## 3. Product Vision

Awtad should feel like a cooperative mobile game rather than a task-management application.

The experience should be:

- Encouraging rather than judgmental.
- Cooperative rather than aggressively competitive.
- Visually engaging and character-driven.
- Private and respectful.
- Simple enough for daily use.
- Reliable even when the internet connection is weak.

## 4. Target Users

The initial target is two friends who want to follow the same daily spiritual routine.

The backend must support many independent pairs, even though each user can have only one active companion in the first release.

## 5. Core Product Principles

- Completion is self-reported.
- The application does not evaluate a user’s faith or sincerity.
- Notifications must never use religious guilt or humiliation.
- A missed day must be represented honestly.
- Private task details are not shared automatically.
- Progress and rewards must be calculated by the backend.
- The application must be Arabic-first and fully support RTL layouts.

## 6. Accounts and Pairing

- A user can create and manage an account.
- A user can generate or receive a companion invitation code.
- A user can accept an invitation to create a pair.
- Each user can have one active companion in the first version.
- Each pair has a private shared routine.
- The data model must allow group support in a future version.

## 7. Shared Daily Plan

- Both companions follow the same shared routine.
- Each task has a title, icon, points value, type, and display order.
- Tasks are divided into:
    - Required tasks.
    - Optional tasks.
- Completing all required tasks completes the user’s daily goal.
- Optional tasks provide additional XP, coins, or rewards.
- Each companion records completion independently.
- A task cannot be completed more than once by the same user on the same local date.
- A completion recorded by mistake can be undone.

Personal routines are outside the first release and may be added later.

## 8. Progress Journey

The home screen must present progress as a shared game journey.

- The screen contains two parallel paths inside one visual scene.
- Each companion has a separate character.
- Completing a task moves the user’s character forward.
- The companion sees the other character’s position and progress count.
- The companion does not see exactly which tasks were completed.
- Reaching the end of the required path completes the personal daily goal.
- When both characters reach the end, the shared daily gate is activated.
- Optional tasks can unlock side rewards, coins, or treasure boxes.

## 9. Streak System

Awtad contains two independent streaks.

### Personal Streak

The personal streak continues when the user completes all required tasks for the local day.

### Companion Streak

The companion streak continues only when both users complete all required tasks for the same local day.

A user’s personal progress must not be erased because the companion missed a day.

## 10. Streak Freeze

- A streak freeze can be purchased using in-app coins.
- It must be owned before the missed day.
- It is applied automatically when required.
- It protects the streak number but does not mark the day as completed.
- A frozen day provides no XP or completion rewards.
- Frozen days are displayed using a clear ice indicator.
- The number of stored freezes must be limited.

## 11. Gamification

The reward system may include:

- XP for progression and levels.
- Coins that can be spent.
- Personal streaks.
- Companion streaks.
- Streak freezes.
- Achievement badges.
- Celebration animations.
- Weekly cooperative challenges.
- Cosmetic characters and path themes.

XP and coins must remain separate:

- XP increases the user’s level and cannot be spent.
- Coins can be used to purchase freezes and cosmetic rewards.

## 12. Notifications

The application should support:

- Morning reminders.
- Incomplete-goal reminders.
- Companion progress notifications.
- Shared-streak warnings.
- Weekly challenge updates.
- Quiet hours.
- Notification frequency controls.
- Optional funny or playful notification styles.

Notifications must be supportive and must not use shame-based language.

## 13. Privacy

A companion can see:

- The other character’s position.
- The number or percentage of completed tasks.
- Whether the daily required goal was completed.
- Personal and shared streak values.

A companion cannot see:

- The exact completed task names.
- Private account information.
- Authentication information.
- Device information.

## 14. Offline Support

- Today’s routine must remain visible without internet access.
- Task completion can be saved locally.
- Pending changes must synchronize when connectivity returns.
- Synchronization must not create duplicate completions or rewards.
- Conflict handling must preserve server-authoritative streak and reward calculations.

## 15. MVP Scope

The first functional release will include:

- Registration and login.
- Companion invitation and pairing.
- One shared daily plan.
- Required and optional tasks.
- Task completion and undo.
- Two-character progress journey.
- Personal and companion streaks.
- XP and coins.
- Basic streak freeze.
- Daily history.
- Local reminders.
- Basic companion progress notifications.

## 16. Future Scope

Future releases may include:

- Groups with more than two users.
- Personal routines.
- Multiple shared plans.
- Seasonal journeys.
- Character customization.
- Advanced achievements.
- More detailed statistics.
- A web administration dashboard.
- Social challenges between pairs.
- Additional languages.

## 17. Non-Functional Requirements

- Secure password storage.
- JWT-based authentication.
- Server-authoritative reward and streak calculations.
- Correct timezone and local-date handling.
- Idempotent task-completion requests.
- Arabic RTL support.
- Accessible colors and touch targets.
- Offline-first data access.
- Automated backend and Android tests.
- API documentation.
- No secrets committed to the public repository.

## 18. Open Product Decisions

The following decisions will be finalized before implementation:

- Who can edit the shared plan.
- Exact XP and coin values.
- Maximum stored streak freezes.
- Character and visual-world theme.
- Notification schedule and tone.
- Weekly challenge rules.