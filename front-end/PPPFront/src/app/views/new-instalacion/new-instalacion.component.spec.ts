import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewInstalacionComponent } from './new-instalacion.component';

describe('NewInstalacionComponent', () => {
  let component: NewInstalacionComponent;
  let fixture: ComponentFixture<NewInstalacionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewInstalacionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewInstalacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
