import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientListComponent } from './components/client/client-list/client-list.component';
import { ClientFormComponent } from './components/client/client-form/client-form.component';
import { ProcessorListComponent } from './components/processor/processor-list/processor-list.component';
import { ProcessorFormComponent } from './components/processor/processor-form/processor-form.component';
import { MotherboardFormComponent } from './components/motherboard/motherboard-form/motherboard-form.component';
import { MotherboardListComponent } from './components/motherboard/motherboard-list/motherboard-list.component';
import { GraphicCardFormComponent } from './components/graphic-card/graphic-card-form/graphic-card-form.component';
import { GraphicCardListComponent } from './components/graphic-card/graphic-card-list/graphic-card-list.component';
import { ProductFormComponent } from './components/product/product-form/product-form.component';
import { ProductListComponent } from './components/product/product-list/product-list.component';
import { ReviewFormComponent } from './components/review/review-form/review-form.component';
import { ReviewListComponent } from './components/review/review-list/review-list.component';
import { OrderFormComponent } from './components/order/order-form/order-form.component';
import { OrderListComponent } from './components/order/order-list/order-list.component';
import { HomeComponent } from './components/home/home.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'clients', component: ClientListComponent },
  { path: 'clients/add', component: ClientFormComponent },
  { path: 'clients/edit/:id', component: ClientFormComponent },
  { path: 'processors', component: ProcessorListComponent },
  { path: 'processors/add', component: ProcessorFormComponent },
  { path: 'processors/edit/:id', component: ProcessorFormComponent },
  { path: 'motherboards', component: MotherboardListComponent },
  { path: 'motherboards/add', component: MotherboardFormComponent },
  { path: 'motherboards/edit/:id', component: MotherboardFormComponent },
  { path: 'graphic-cards', component: GraphicCardListComponent },
  { path: 'graphic-cards/add', component: GraphicCardFormComponent },
  { path: 'graphic-cards/edit/:id', component: GraphicCardFormComponent },
  { path: 'products', component: ProductListComponent },
  { path: 'products/add', component: ProductFormComponent },
  { path: 'products/edit/:id', component: ProductFormComponent },
  { path: 'reviews', component: ReviewListComponent },
  { path: 'reviews/add', component: ReviewFormComponent },
  { path: 'reviews/edit/:id', component: ReviewFormComponent },
  { path: 'orders', component: OrderListComponent },
  { path: 'orders/add', component: OrderFormComponent },
  { path: 'orders/edit/:id', component: OrderFormComponent },
  { path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }