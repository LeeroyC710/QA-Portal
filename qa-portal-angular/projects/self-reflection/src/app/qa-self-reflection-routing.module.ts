import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {TrainerReflectionComponent} from './trainer-reflection/trainer-reflection.component';
import {CohortSummaryComponent} from './cohort-summary/cohort-summary.component';
import {TraineeReflectionComponent} from './trainee-reflection/trainee-reflection.component';
import {SelfReflectionHistoryComponent} from './self-reflection-history/self-reflection-history.component';
import {CohortTraineesComponent} from './cohort-trainees/cohort-trainees.component';
import {TraineeNewReflectionComponent} from './trainee-new-reflection/trainee-new-reflection.component';
import {TRAINEE_ROLE, TRAINER_ROLE, TRAINING_ADMIN_ROLE} from '../../../portal-core/src/app/_common/models/portal-constants';
import {AppAuthGuard} from '../../../portal-core/src/app/_common/guards/app-auth-guard';

const routes: Routes = [
  {
    path: 'trainee',
    children: [
      {
        path: 'new', component: TraineeNewReflectionComponent,
        canActivate: [AppAuthGuard],
        data: {
          roles: [
            TRAINEE_ROLE
          ]
        }
      },
      {
        path: ':id', component: TraineeReflectionComponent,
        canActivate: [AppAuthGuard],
        data: {
          roles: [
            TRAINEE_ROLE
          ]
        }
      } ,
      {
        path: 'history', component: SelfReflectionHistoryComponent,
        canActivate: [AppAuthGuard],
        data: {
          roles: [
            TRAINEE_ROLE
          ]
        }
      }
    ]
  },
  {
    path: 'trainer',
    children: [
      {
        path: 'trainees', component: CohortTraineesComponent,
        canActivate: [AppAuthGuard],
        data: {
          roles: [
            TRAINER_ROLE
          ]
        }
      },
      {
        path: 'trainee/:id', component: TrainerReflectionComponent,
        canActivate: [AppAuthGuard],
        data: {
          roles: [
            TRAINER_ROLE
          ]
        }
      }
    ]
  },
  {
    path: 'admin',
    children: [
      {
        path: 'cohorts', component: CohortSummaryComponent,
        canActivate: [AppAuthGuard],
        data: {
          roles: [
            TRAINING_ADMIN_ROLE
          ]
        }
      }
    ]
  }
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}