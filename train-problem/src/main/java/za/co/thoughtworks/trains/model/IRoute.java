package za.co.thoughtworks.trains.model;

import za.co.thoughtworks.trains.application.Distance;

public interface IRoute extends Path {

	public abstract Distance getTotalDistance();

}