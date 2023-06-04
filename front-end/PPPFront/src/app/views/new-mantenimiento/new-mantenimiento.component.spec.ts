import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewMantenimientoComponent } from './new-mantenimiento.component';

describe('NewMantenimientoComponent', () => {
  let component: NewMantenimientoComponent;
  let fixture: ComponentFixture<NewMantenimientoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewMantenimientoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewMantenimientoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
